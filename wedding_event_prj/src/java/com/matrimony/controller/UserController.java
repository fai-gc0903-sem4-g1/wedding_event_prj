/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import com.matrimony.database.AccountDAO;
import com.matrimony.entity.Account;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author SON
 */
@Controller
public class UserController {
    @RequestMapping(value = "{username}")
    public String doProfile(@PathVariable("username") String username, Model model) {
        System.out.println(username);
        Account account = AccountDAO.findByUsername(username);
        if (account == null) {
            return "userNotFound";
        } else {
            model.addAttribute("account", account);
            return "profile";
        }
    }

    @RequestMapping(value = "settings", method = RequestMethod.POST)
    public String viewSettings(HttpServletRequest request) {
        System.out.println("sau day la session");
        Account account = (Account) request.getSession().getAttribute("account");
        try {
            System.out.println(account);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "settings";
    }

}
