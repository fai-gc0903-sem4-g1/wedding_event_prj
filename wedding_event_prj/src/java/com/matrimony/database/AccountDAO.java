/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.database;

import com.matrimony.entity.Account;
import com.matrimony.exception.STException;
import com.matrimony.security.HashUtil;
import com.matrimony.util.HibernateUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public static Account findById(String id){
        Session ss=HibernateUtil.getSession();
        Account account=(Account) ss.createQuery("FROM account where accountId=?").setString(0, id).uniqueResult();
        ss.close();
        return account;
    }
    
        public static Account findByUsername(String username){
        Session ss=HibernateUtil.getSession();
        Account account=(Account) ss.createQuery("FROM account where username=?").setString(0, username).uniqueResult();
        ss.close();
        return account;
    }
    
    public static boolean Update(Account account){
        return false;
    }
    
    public static Account login(String username, String password) throws STException.UsernameNotExist, STException.WrongPassword{
        Account account=findByUsername(username);
        if(account==null)throw new STException.UsernameNotExist("username not exists");
        String passwordTemp=HashUtil.hashPassword(password, account.getSalt());
        if(account.getPasswordHash().equals(passwordTemp)){
            return account;
        }else throw new STException.WrongPassword("Wrong password");
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(login("taothichthe", "duockhong"));
        } catch (STException.UsernameNotExist ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (STException.WrongPassword ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
