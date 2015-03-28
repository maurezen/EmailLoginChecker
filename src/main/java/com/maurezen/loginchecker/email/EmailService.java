package com.maurezen.loginchecker.email;

import com.maurezen.loginchecker.email.impl.Aol;
import com.maurezen.loginchecker.email.impl.Gmail;
import com.maurezen.loginchecker.email.impl.UnknownEmail;

/**
 *
 */
public class EmailService {

    private static final String GMAIL = "@gmail.com";
    private static final String AOL = "@aol.com";

    public static final Gmail GMAIL_SERVICE = new Gmail();
    public static final Aol AOL_SERVICE = new Aol();
    public static final UnknownEmail UNKNOWN_EMAIL = new UnknownEmail();

    public static Email create(String login) {
        if (login.endsWith(GMAIL)) {
            return GMAIL_SERVICE;
        } else if (login.endsWith(AOL)) {
            return AOL_SERVICE;
        } else {
            return UNKNOWN_EMAIL;
        }
    }
}
