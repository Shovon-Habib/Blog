package com.dev.utils;

import java.util.regex.Pattern;

public class Utils {

    public static final String MAIL_CHECKING_REGEX = "^(.+)@(.+)$";
    public static final String USERNAME_CHARS_REGEX = "[^A-Za-z0-9]";

    public static final String ROLE_ADMIN = "Admin";
    public static final String ROLE_BLOGGER = "Blogger";

    public static boolean isEmail(String mail) {
        return Pattern.compile(MAIL_CHECKING_REGEX).matcher(mail).matches();
    }
}
