package com.petstore.security;
/*
 *@author tobi
 * created on 09/05/2020
 *
 */

public final class SecurityConstansts {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/student";
//    public static final String ADMIN_URL = "/api/admins";
//    public static final String INVESTMENT_URL = "/api/investments";
//    public static final String PASSWORD_RESET_URL= "/api/password/reset";
//    public static final String PASSWORD_CONFIRMATION= "/api/password/confirm";
//    public static final String PASSWORD_NEW_PASSWORD= "/api/password/new";
}
