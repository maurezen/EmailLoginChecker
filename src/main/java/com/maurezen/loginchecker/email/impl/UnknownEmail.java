package com.maurezen.loginchecker.email.impl;

import com.maurezen.loginchecker.email.Email;

import javax.mail.Session;

/**
 *
 */
public class UnknownEmail implements Email {

    public static final Session WRONG_SESSION = null;

    @Override
    public boolean test(Session session) {
        return false;
    }

    @Override
    public Session login(String username, String password) {
        return WRONG_SESSION;
    }
}
