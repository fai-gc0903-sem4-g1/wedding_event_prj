/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.database;

import com.matrimony.entity.Account;
import com.matrimony.entity.UserProfile;
import com.matrimony.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author SON
 */
public class UserProfileDAO {

    public static void add(UserProfile a) {
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

    public static Account findById(String id) {
        Session ss = HibernateUtil.getSession();
        Account account = (Account) ss.createQuery("FROM account where accountId=?").setString(0, id).uniqueResult();
        ss.close();
        return account;
    }
    public static boolean Update(Account account) {
        return false;
    }
    
    public static void main(String[] args) {
        
    }
}
