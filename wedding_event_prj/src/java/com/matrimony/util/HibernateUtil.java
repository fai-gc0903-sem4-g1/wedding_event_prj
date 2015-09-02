/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author SON
 */
public class HibernateUtil {

//    protected static ServiceRegistry sr;
//    protected static SessionFactory sf;
    private static Configuration cfg;
    public static Session getSession() {
        if(cfg==null){
            cfg = new Configuration();
            cfg.configure();
        }
        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory sf = cfg.buildSessionFactory(sr);
        Session ss = sf.openSession();
        return ss;
    }

    public static void main(String[] args) {
        HibernateUtil.getSession().getTransaction().commit();
    }
}
