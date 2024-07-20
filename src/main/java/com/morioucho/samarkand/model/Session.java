package com.morioucho.samarkand.model;

import java.security.SecureRandom;
import java.util.Base64;
import java.time.Instant;

public class Session {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    private String token;

    public static Session generateToken(){
        Session session = new Session();

        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);

        session.setToken(base64Encoder.encodeToString(randomBytes));

        return session;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
