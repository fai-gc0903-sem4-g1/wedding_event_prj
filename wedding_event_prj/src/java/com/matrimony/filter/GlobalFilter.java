/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.filter;

import com.matrimony.database.AccountDAO;
import com.matrimony.entity.Account;
import java.io.IOException;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SON
 */
public class GlobalFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        System.out.println("Global filter");
        System.out.println(new Date().toString() + ": " + request.getRemoteAddr());
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            Cookie[] allCookie = request.getCookies();
            boolean keepLogin = false;
            for (Cookie c : allCookie) {
                System.out.println(c.getName() + " " + c.getValue());
            }
            for (Cookie c : allCookie) {
                if ("keepLoggin".equals(c.getName())) {
                    if ("true".equals(c.getValue())) {
                        keepLogin = true;
                        break;
                    }
                }
            }
            System.out.println("keep loggin confirm " + keepLogin);
            for (Cookie c : allCookie) {
                if (keepLogin && "loginName".equals(c.getName())) {
                    account = AccountDAO.findByLoginName(c.getValue());
                    System.out.println("cokie the nao roi " + account);
                    request.getSession().setAttribute("account", account);
                    break;
                }
            }

        }
        try {
            chain.doFilter(request, response);
        } catch (IOException | ServletException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void destroy() {

    }

}
