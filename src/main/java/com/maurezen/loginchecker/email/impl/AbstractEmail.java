package com.maurezen.loginchecker.email.impl;

import com.maurezen.loginchecker.email.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 */
public abstract class AbstractEmail implements Email {
    @Override
    public boolean test(Session session) {
        try {
            Transport tr = session.getTransport("smtp");
            tr.connect();
            tr.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
