package com.proptit.protify_be.security;

import com.proptit.protify_be.constants.ResponseValue;
import com.proptit.protify_be.exception.ResponseException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class AuthManager {
    @Getter
    private final long accessTokenValidity;
    @Getter
    private final long refreshTokenValidity;
    private final SecretKey secretKey;

    public AuthManager(@Value("${jwt.secret}") String secret, @Value("${jwt.access-token.validity}") long accessTokenValidity, @Value("${jwt.refresh-token.validity}") long refreshTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateAccessToken(long id, String username) {
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + accessTokenValidity);
        return Jwts.builder()
                .id(String.valueOf(id))
                .subject(username)
                .issuedAt(issuedDate)
                .expiration(expiredDate)
                .signWith(secretKey)
                .compact();
    }

    public String generateRefreshToken(long id, String username) {
        return Jwts.builder()
                .id(String.valueOf(id))
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + refreshTokenValidity))
                .signWith(secretKey)
                .compact();
    }

    public Claims decodeToken(String token) throws ResponseException {
        try {
            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
        } catch (JwtException e) {
            throw new ResponseException(ResponseValue.INVALID_TOKEN);
        }
    }
}
