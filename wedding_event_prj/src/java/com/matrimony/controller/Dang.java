/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import com.matrimony.database.AccountDAO;
import com.matrimony.entity.Account;
import com.matrimony.exception.STException;
import com.matrimony.security.HashUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author LaptopF5
 */
@Controller
public class Dang {
    @RequestMapping(value = "dang", method=RequestMethod.POST)
    public String dang(Account acc){
        String hashed=HashUtil.hashPassword(acc.getPasswordHash());
        acc.setPasswordHash(hashed);
        Account account;
        try {
            account = AccountDAO.login(acc.getUsername(), acc.getPasswordHash());
        } catch (STException.UsernameNotExist ex) {
            Logger.getLogger(Dang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (STException.WrongPassword ex) {
            Logger.getLogger(Dang.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(account==null){
            return "fail";
        }
        return "index";
    }
    @RequestMapping(value = "dang1", method=RequestMethod.GET)
    public String dang1(){
        return "login";
    }
    @RequestMapping(value = "dang2", method=RequestMethod.GET)
    public String dang2(){
        return "login";
    }
}
