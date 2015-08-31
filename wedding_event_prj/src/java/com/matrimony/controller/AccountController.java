/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import com.matrimony.database.AccountDAO;
import com.matrimony.entity.Account;
import com.matrimony.entity.UserProfile;
import com.matrimony.exception.STException;
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
    
    @RequestMapping(value="qlogin", method = RequestMethod.GET)
    public String qlogin(HttpServletRequest request, Account account){
        if(!"".equals(account.getUsername()) && !"".equals(account.getPasswordHash())){
            try {
                AccountDAO.login(account.getUsername(), null);
            } catch (STException.UsernameNotExist ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("alert", "Tài khoản không tồn tại");
            } catch (STException.WrongPassword ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("alert", "Sai password");
            }
            return "failed";
        }
        return "login";
    }
    
    @RequestMapping(value="qregister", method = RequestMethod.GET)
    public String qregister(Account account, UserProfile userProfile){
        return "register";
    }
}
