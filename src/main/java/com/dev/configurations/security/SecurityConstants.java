package com.dev.configurations.security;

import java.util.Arrays;
import java.util.List;

public class SecurityConstants {


    public static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static final String SYSTEM_ACCOUNT = "system"; // 10 days

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    public static final String SIGN_UP_URL = "/api/blogger/registration";

    public static final String[] AUTH_WHITE_LIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"};
}
