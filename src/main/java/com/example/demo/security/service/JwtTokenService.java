package com.example.demo.security.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {

    private final JwtEncoder encoder;

    @Value("${security.jwt.issuer:my-app}")
    private String issuer;

    @Value("${security.jwt.expires-minutes:60}")
    private long expiresMinutes;

    public JwtTokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(String subject, Map<String, Object> claims) {
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(now.plus(expiresMinutes, ChronoUnit.MINUTES))
                .subject(subject)
                .claims(c -> c.putAll(claims))
                .build();

        return encoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
}
