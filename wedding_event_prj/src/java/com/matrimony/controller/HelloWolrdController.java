/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import com.matrimony.entity.Person;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author SON
 */
@Controller
public class HelloWolrdController {

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "form";
    }

    @RequestMapping(value = "valid", method = RequestMethod.POST)
    public String valid(@Valid Person p, BindingResult bindingResult) {
        System.out.println(p);
        if (bindingResult.hasErrors()) {
            return "failed";
        }
        return "redirect:OK";
    }
}
