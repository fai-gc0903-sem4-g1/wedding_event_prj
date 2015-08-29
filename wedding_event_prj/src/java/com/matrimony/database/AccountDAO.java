/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.database;

import com.matrimony.entity.Account;
import com.matrimony.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author SON
 */
public class AccountDAO {
    public static void add(Account a) {
        Session ss = HibernateUtil.getSession();
        ss.getTransaction().begin();
        ss.save(a);
        ss.getTransaction().commit();
        ss.close();
    }

    public static List<Account> allAccounts() {
        Session ss = HibernateUtil.getSession();
        List<Account> accounts = ss.createQuery("FROM account").list();
        ss.close();
        return accounts;
    }
    
    public static Account login(String username, String password){
        Session ss=HibernateUtil.getSession();
        Query q=ss.createQuery("FROM account WHERE username=? and passwordHash=?");
        q.setString(0, username);
        q.setString(1, password);
        return (Account) q.uniqueResult();
    }
    
    public static void main(String[] args) {
        System.out.println(login("taothichthe", "duockhong"));
    }
}
