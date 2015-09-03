/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import com.matrimony.database.AccountDAO;
import com.matrimony.database.UserProfileDAO;
import com.matrimony.entity.Account;
import com.matrimony.entity.UserProfile;
import com.matrimony.exception.STException;
import com.matrimony.util.MailUtil;
import facebook.api.FBConnection;
import facebook.api.FBGraph;
import facebook.entity.FBProfile;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author SON
 */
@Controller
@RequestMapping(value = "account")
public class AccountController {
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String qogin(HttpServletRequest request, Account accountLogin, HttpSession session) {
        System.out.println(accountLogin);
        if (!"".equals(accountLogin.getUsername()) && !"".equals(accountLogin.getPasswordHash())) {
            try {
                Account account = AccountDAO.login(accountLogin.getUsername(), accountLogin.getPasswordHash());
                account.setLastTimeLogin(new Timestamp(System.currentTimeMillis()));
                account.setLastIPLogin(request.getRemoteAddr());
                AccountDAO.Update(account);
                session.setAttribute("account", account);
                System.out.println(account.getUsername() + " logged in");
                if (account.isActivated()) {
                    return "index";
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
        return "404";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(HttpServletRequest request, Account accountReg, UserProfile userProfileReg, String day, String month, String year) {
        System.out.println(accountReg);
        accountReg.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
        accountReg.setRegistrationIP(request.getRemoteAddr());
        String activeKey = UUID.randomUUID().toString().toUpperCase(); //Generate key active
        accountReg.setActiveKey(activeKey);
        accountReg.setRegMethod("native");
        try {
            AccountDAO.add(accountReg);

            /*=====Create user profile=====*/
            Account a = AccountDAO.findByUsername(accountReg.getUsername());
            Date birthday = Date.valueOf(year + "-" + month + "-" + day);
            userProfileReg.setAccountId(a.getAccountId());
            userProfileReg.setBirthday(birthday);
            System.out.println(userProfileReg);
            UserProfileDAO.add(userProfileReg);

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
            return "active";
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
    public String active(HttpServletRequest request, String activeKey) {
        Account curAccount = (Account) request.getSession().getAttribute("account");
        if (curAccount.getActiveKey().equals(activeKey)) {
            curAccount.setActivated(true);
            curAccount.setActiveTime(new Timestamp(System.currentTimeMillis()));
            AccountDAO.Update(curAccount);
            return "redirect:/";
        } else {
            return "active";
        }
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
        System.out.println(code);
        return "notyet";
    }
}
