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

    protected static final Configuration cfg = new Configuration();
    protected static ServiceRegistry sr;
    protected static SessionFactory sf;

    public static Session getSession() {
        cfg.configure();
        sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        sf = cfg.buildSessionFactory(sr);
        return sf.openSession();
    }

    public static void main(String[] args) {
        System.out.println(HibernateUtil.getSession());
    }
}
