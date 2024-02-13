package by.itacademy.report.service;

import by.itacademy.report.core.dto.UserDetailsDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public boolean isTokenValid(String token) {

        try {
            Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
            return true;
        } catch (RuntimeException e) {
            logger.debug(e.getMessage());
        }
        return false;

    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetailsDTO userDetailsDTO) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetailsDTO.getRole());
        claims.put("uuid", userDetailsDTO.getUuid());
        claims.put("fio", userDetailsDTO.getFio());

        return Jwts.builder()
                .subject(userDetailsDTO.getMail())
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSecretKey()).compact();

    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).after(new Date());
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractUserRole(String token) {
        Claims claims = extractAllClaims(token);

        return claims.get("role", String.class);
    }

    public String extractUserFIO(String token) {
        Claims claims = extractAllClaims(token);

        return claims.get("fio", String.class);
    }

    public String extractUserUUID(String token) {
        Claims claims = extractAllClaims(token);

        return claims.get("uuid", String.class);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

}
