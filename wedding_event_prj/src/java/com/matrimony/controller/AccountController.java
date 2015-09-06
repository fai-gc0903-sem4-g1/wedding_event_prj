/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import com.matrimony.database.AccountDAO;
import com.matrimony.entity.Account;
import com.matrimony.exception.STException;
import com.matrimony.util.MailUtil;
import facebook.api.FBConnection;
import facebook.api.FBGraph;
import facebook.entity.FBProfile;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author SON
 */
@Controller
public class AccountController {

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String doLogin(HttpServletResponse response, HttpServletRequest request, @Valid @ModelAttribute("accountLogin") Account accountLogin, BindingResult bindingResult, HttpSession session, String keepLoggin) {
        if (bindingResult.hasFieldErrors("username") || bindingResult.hasFieldErrors("passwordHash")) {
            System.out.println("form login error");
            return "index";
        }
        try {
            Account account = AccountDAO.login(accountLogin.getUsername(), accountLogin.getPasswordHash());
            account.setLastTimeLogin(new Timestamp(System.currentTimeMillis()));
            account.setLastIPLogin(request.getRemoteAddr());
            AccountDAO.Update(account);
            session.setAttribute("account", account);
            System.out.println(account.getUsername() + " logged in");
            if (keepLoggin != null) {
                Cookie[] cookies = new Cookie[3];
                cookies[0] = new Cookie("loginName", accountLogin.getUsername());
                cookies[1] = new Cookie("password", accountLogin.getPasswordHash());
                cookies[2] = new Cookie("keepLoggin", "true");
                for (Cookie c : cookies) {
                    c.setMaxAge(60 * 60 * 24 * 365);
                    response.addCookie(c);
                }
            }
            if (account.isActivated()) {
                return "redirect:";
            } else {
                return "active";
            }
        } catch (STException.UsernameNotExist ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("notice", "Tài khoản không tồn tại, chúng tôi k tìm thấy tên tài khoản, email hay số điện thoại");
        } catch (STException.WrongPassword ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("notice", "Sai password");
        }
        return "failed";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String doRegister(HttpServletRequest request, @Valid @ModelAttribute("accountReg") Account accountReg, BindingResult bindingResult, String day, String month, String year) {
        System.out.println(accountReg);
        Date birthday = null;
        if (bindingResult.hasErrors()) {
            try {
                birthday = Date.valueOf(year + "-" + month + "-" + day);
            } catch (IllegalArgumentException ex) {

                System.out.println(ex + ": Date not correct");
                request.setAttribute("birthdayValid", "Ngày tháng chọn sai");
            }
            return "index";
        }
        accountReg.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
        accountReg.setRegistrationIP(request.getRemoteAddr());
        String activeKey = UUID.randomUUID().toString().toUpperCase(); //Generate key active
        accountReg.setActiveKey(activeKey);
        accountReg.setRegMethod("native");
        try {
            accountReg.setBirthday(birthday);
            AccountDAO.add(accountReg);
            /*=====Tao noi dung email can gui=====*/
            String subject = "Chao mung den voi matrimony, kich hoat tai khoan";
            StringBuilder content = new StringBuilder();
            content.append("Day la key active: ");
            content.append(activeKey);
            content.append("\n");
            content.append("Cam on da su dung dich vu cua chung toi!");

            /*=====Send mail to active=====*/
            MailUtil mailUtil = new MailUtil(accountReg.getEmail(), subject, content.toString());
            mailUtil.send();
            request.getSession().setAttribute("account", AccountDAO.findByUsername(accountReg.getUsername()));
            return "redirect:active";
        } catch (STException.UsernameAlready ex) {
            System.out.println(ex.getMessage());
            request.setAttribute("notice", "UsernameAlready");
            return "failed";
        } catch (STException.EmailAlready ex) {
            System.out.println(ex.getMessage());
            request.setAttribute("notice", "EmailAlready");
            return "failed";
        } catch (STException.ContactNumberAlready ex) {
            System.out.println(ex.getMessage());
            request.setAttribute("notice", "ContactNumberAlready");
            return "failed";
        }
    }

    @RequestMapping(value = "active", method = RequestMethod.POST)
    public String doActive(HttpServletRequest request, String activeKey) {
        Account curAccount = (Account) request.getSession().getAttribute("account");
        if (curAccount.getActiveKey().equals(activeKey)) {
            curAccount.setActivated(true);
            curAccount.setActiveTime(new Timestamp(System.currentTimeMillis()));
            AccountDAO.Update(curAccount);
            return "redirect:";
        } else {
            return "active";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public String doLogout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] allCookie = request.getCookies();
        for (Cookie c : allCookie) {
            if ("keepLoggin".equals(c.getName())) {
                c.setValue("false");
                response.addCookie(c);
            }
        }
        session.setAttribute("account", null);
        return "redirect:";
    }

    @RequestMapping(value = "loginWithFacebook", method = RequestMethod.GET)
    public String loginWithFacebook(HttpServletRequest request, String code) {
        FBConnection fb = new FBConnection();
        System.out.println(code);
        FBGraph fbGraph = new FBGraph();
        FBProfile profile = fbGraph.getFBProfile(code);
        return null;
    }

    @RequestMapping(value = "FBRedirect", method = RequestMethod.GET)
    public String FBRedirect(String code) {
        FBConnection fBConnection = new FBConnection();
        try {
            String accessToken = fBConnection.getAccessToken(code);
            System.out.println(accessToken);
            FBGraph fBGraph = new FBGraph();
            FBProfile prof = fBGraph.getFBProfile(accessToken);
            System.out.println(prof);
            Account aFacbook=new Account();
            aFacbook.setEmail(prof.getEmail());
            aFacbook.setActivated(prof.isVerified());
            aFacbook.setFirstName(prof.getFirst_name());
            aFacbook.setLastName(prof.getLast_name());
            aFacbook.setGender(prof.getGender());
            return "redirect:";
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return "userNotFound";
        }
    }

}
