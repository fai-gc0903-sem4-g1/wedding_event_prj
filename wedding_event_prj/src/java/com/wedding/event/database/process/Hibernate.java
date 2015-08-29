/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wedding.event.database.process;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author SON
 */
public class Hibernate {

    protected static final Configuration cfg = new Configuration();
    protected static ServiceRegistry sr;
    protected static SessionFactory sf;
    public static Session session = getSession();

    private static Session getSession() {
        if (session == null) {
            cfg.configure();
            sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            sf = cfg.buildSessionFactory(sr);
            session = sf.openSession();
        }
        return session;
    }

    public static void close() {
        sf.close();
    }

    public static void main(String[] args) {
        System.out.println(Hibernate.session);
        close();
    }
}
