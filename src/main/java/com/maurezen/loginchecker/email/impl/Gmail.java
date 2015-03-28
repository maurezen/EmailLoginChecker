package com.maurezen.loginchecker.email.impl;

import javax.mail.*;
import java.util.Properties;

/**
 *
 */
public class Gmail extends AbstractEmail {

    @Override
    public boolean test(Session session) {
        try {
            Transport tr = session.getTransport("smtp");
            tr.connect();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            if (e.getMessage().contains("534-5.7.14")) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public Session login(String username, String password) {
        return createTLSSession(username, password);
    }

    private static Session createTLSSession(final String login, final String password) {
        Properties props = new Properties(System.getProperties());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", login);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");

        return Session.getInstance(
            props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(login, password);
                }
            }
        );
    }
}
