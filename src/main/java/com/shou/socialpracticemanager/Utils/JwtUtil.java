package com.shou.socialpracticemanager.Utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;


@Component
public class JwtUtil {
    private static final String salt = "1959234";

    public static String doGenerateToken(Map<String, String> claims) {
        JWTCreator.Builder builder = JWT.create();
        claims.forEach(builder::withClaim);
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, 24);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(salt));
    }

    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(salt)).build().verify(token);
    }

    public static String getUserNameFromToken(String token) {
        DecodedJWT decodedJWT = verify(token);
        return decodedJWT.getClaims().get("username").asString();
    }

    public static int getUserIdFromToken(String token) {
        DecodedJWT decodedJWT = verify(token);
        return decodedJWT.getClaims().get("userid").asInt();
    }
}
