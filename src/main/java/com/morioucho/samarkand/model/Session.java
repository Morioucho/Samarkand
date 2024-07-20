package com.morioucho.samarkand.model;

import jakarta.persistence.*;

import java.security.SecureRandom;
import java.util.Base64;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
