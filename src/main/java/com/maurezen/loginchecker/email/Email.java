package com.maurezen.loginchecker.email;

import javax.mail.Session;

/**
 *
 */
public interface Email {

    Session login(String username, String password);

    boolean test(Session session);
}
