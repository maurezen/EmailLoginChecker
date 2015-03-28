package com.maurezen.loginchecker.email.impl;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 *
 */
public class Yahoo extends AbstractEmail {
    @Override
    public Session login(String username, String password) {
        Properties props = new Properties(System.getProperties());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");

        return Session.getInstance(
            props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            }
        );
    }
}
