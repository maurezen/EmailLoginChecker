package com.maurezen.loginchecker.email;

import com.maurezen.loginchecker.email.impl.Gmail;
import com.maurezen.loginchecker.email.impl.UnknownEmail;

/**
 *
 */
public class EmailService {

    private static final String GMAIL = "@gmail.com";

    public static Email create(String login) {
        if (login.endsWith(GMAIL)) {
            return new Gmail();
        } else {
            return new UnknownEmail();
        }
    }
}
