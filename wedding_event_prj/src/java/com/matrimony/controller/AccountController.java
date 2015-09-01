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
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author SON
 */
@Controller
@RequestMapping(value="account")
public class AccountController {
    @RequestMapping(value="login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    
    @RequestMapping(value="register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }
    
    @RequestMapping(value="qlogin", method = RequestMethod.POST)
    public String qlogin(HttpServletRequest request, Account account){
        System.out.println(account);
        if(!"".equals(account.getUsername()) && !"".equals(account.getPasswordHash())){
            try {
                AccountDAO.login(account.getUsername(), account.getPasswordHash());
                request.setAttribute("notice", "Đăng nhập thành công");
                return "success";
            } catch (STException.UsernameNotExist ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("notice", "Tài khoản không tồn tại");
            } catch (STException.WrongPassword ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("notice", "Sai password");
            }
            return "failed";
        }
        return "404";
    }
    
    @RequestMapping(value="qregister", method = RequestMethod.POST)
    public String qregister(HttpServletRequest request,Account account, String day, String month, String year){
        System.out.println(account);
        account.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
        account.setRegistrationIP(request.getRemoteAddr());
        String activeKey=UUID.randomUUID().toString().toUpperCase();
        account.setActiveKey(activeKey);
        try {
            AccountDAO.add(account);
            Account a=AccountDAO.findByUsername(account.getUsername());
            Date birthday=Date.valueOf(year+"-"+month+"-"+day);
            UserProfile userProfile=new UserProfile();
            userProfile.setAccountId(a.getAccountId());
            userProfile.setBirthday(birthday);
            UserProfileDAO.add(userProfile);
        } catch (STException.UsernameAlready ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("notice", "UsernameAlready");
        } catch (STException.EmailAlready ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("notice", "EmailAlready");
        } catch (STException.ContactNumberAlready ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("notice", "ContactNumberAlready");
        }
        return "success";
    }
}
