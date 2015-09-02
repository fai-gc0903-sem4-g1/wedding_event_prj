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
import java.sql.Timestamp;
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

    public static void add(Account a) throws STException.UsernameAlready, STException.EmailAlready, STException.ContactNumberAlready {
        if (findByUsername(a.getUsername()) != null) {
            throw new STException.UsernameAlready("username already");
        } else if (findByEmail(a.getEmail()) != null) {
            throw new STException.EmailAlready("email already");
        } else if (findByContactNumber(a.getContactNumber())!=null) {
            throw new STException.ContactNumberAlready("contact number already");
        } else {
            a.setSalt(HashUtil.generateSalt(a.getUsername()));
            a.setPasswordHash(HashUtil.hashPassword(a.getPasswordHash(), a.getSalt()));
            Session ss = HibernateUtil.getSession();
            ss.getTransaction().begin();
            ss.save(a);
            ss.getTransaction().commit();
            ss.close();
        }
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

    public static Account findByUsername(String username) {
        Session ss = HibernateUtil.getSession();
        Account account = (Account) ss.createQuery("FROM account where username=?").setString(0, username).uniqueResult();
        ss.close();
        return account;
    }

    public static Account findByEmail(String username) {
        Session ss = HibernateUtil.getSession();
        Account account = (Account) ss.createQuery("FROM account where email=?").setString(0, username).uniqueResult();
        ss.close();
        return account;
    }

    public static Account findByContactNumber(String contactNumber) {
        Session ss = HibernateUtil.getSession();
        Account account = (Account) ss.createQuery("FROM account where contactNumber=?").setString(0, contactNumber).uniqueResult();
        ss.close();
        return account;
    }

    public static void Update(Account account) {
       Session ss=HibernateUtil.getSession();
       ss.getTransaction().begin();
       ss.update(account);
       ss.getTransaction().commit();
    }

    public static Account login(String username, String password, String ipAddr) throws STException.UsernameNotExist, STException.WrongPassword {
        Account account;
        if((account=findByUsername(username))==null){
            if((account=findByEmail(username))==null){
                account=findByContactNumber(username);
            }
        }
        if (account == null) {
            throw new STException.UsernameNotExist("username not exists");
        }
        String passwordTemp = HashUtil.hashPassword(password, account.getSalt());
        if (account.getPasswordHash().equals(passwordTemp)) {
            account.setLastTimeLogin(new Timestamp(System.currentTimeMillis()));
            account.setLastIPLogin(ipAddr);
            AccountDAO.Update(account);
            return account;
        } else {
            throw new STException.WrongPassword("Wrong password");
        }
    }
    
    public static void main(String[] args) {
            //        Account a=new Account();
//        a.setUsername("kunedo");
//        a.setPasswordHash("1234");
//        try {
//            System.out.println("Adding...");
//            add(a);
//        } catch (STException.UsernameAlready ex) {
//            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (STException.EmailAlready ex) {
//            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (STException.ContactNumberAlready ex) {
//            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
       
    }
}
