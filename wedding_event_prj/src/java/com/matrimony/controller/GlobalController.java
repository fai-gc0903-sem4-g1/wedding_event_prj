/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import com.matrimony.entity.Account;
import com.matrimony.entity.UserProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author SON
 */
@Controller
public class GlobalController {
    @RequestMapping(value="/")
    public String index(Model model){
        model.addAttribute("accountLogin", new Account());
        model.addAttribute("userProfileReg", new UserProfile());
        System.out.println("HHHHHH");
        return "index";
    }
}
