package com.maurezen.loginchecker.email;

import com.maurezen.loginchecker.email.impl.*;

/**
 *
 */
public class EmailService {

    private static final String GMAIL = "@gmail.com";
    private static final String AOL = "@aol.com";
    private static final String YAHOO = "@yahoo.com";
    private static final String HOTMAIL = "@hotmail.com";
    private static final String OUTLOOK = "@hotmail.com";

    public static final Gmail GMAIL_SERVICE = new Gmail();
    public static final Aol AOL_SERVICE = new Aol();
    public static final Yahoo YAHOO_SERVICE = new Yahoo();
    public static final Hotmail HOTMAIL_SERVICE = new Hotmail();
    public static final UnknownEmail UNKNOWN_EMAIL = new UnknownEmail();

    public static Email get(String login) {
        if (login.endsWith(GMAIL)) {
            return GMAIL_SERVICE;
        } else if (login.endsWith(AOL)) {
            return AOL_SERVICE;
        } else if (login.endsWith(YAHOO)) {
            return YAHOO_SERVICE;
        } /*else if (login.endsWith(HOTMAIL) || login.endsWith(OUTLOOK)) {
            return HOTMAIL_SERVICE;
        } */else {
            return UNKNOWN_EMAIL;
        }
    }
}
