/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.test;

import com.matrimony.entity.Account;
import com.matrimony.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author SON
 */
public class Test {
    public static void add(Account a){
        Session ss=HibernateUtil.getSession();
        ss.getTransaction().begin();
        ss.save(a);
        ss.getTransaction().commit();
        ss.close();
    }
    
    public static List<Account> allAccounts(){
        Session ss=HibernateUtil.getSession();
        List<Account> accounts=ss.createQuery("FROM account").list();
        ss.close();
        return accounts;
    }
    public static void main(String[] args) {
//        Account a=new Account();
//        a.setUsername("taothichthe");
//        a.setPasswordHash("11041995");
//        add(a);
//        System.out.println(allAccounts().size());
        
        Account b=new Account();
        b.setUsername("taothichthe");
        b.setPasswordHash("duockhong");
        Session ss=HibernateUtil.getSession();
        ss.save(b);
        ss.beginTransaction().commit();
        System.out.println(allAccounts().size());
    }
}
