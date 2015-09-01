/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.util;

import javax.mail.Authenticator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author SON
 */
public class MailUtil implements Runnable {

    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String PORT = "587";
    private static final String USER = "kunedo1104@gmail.com";
    private static final String PASS = "damcaoson123";
    private Properties props;
    private Authenticator auth;
    private Session session;
    
    
    private String content;
    private String subject;
    private String destinationEmail;

    public MailUtil(String destinationEmail, String subject, String content) {
        this.destinationEmail=destinationEmail;
        this.subject=subject;
        this.content=content;
        /* =====Create properties===== */
        props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_SERVER);
        props.put("mail.smtp.port", PORT);

        /* =====Authentication===== */
        auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER, PASS);
            }
        };
        session = Session.getInstance(props, auth);

    }
    
    public void send() {
        this.run();
    }

    @Override
    public void run() {
        try {
            /* =====Create mail message===== */
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinationEmail));
            message.setSubject(this.subject);
            message.setText(this.content);
            Transport.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
