package kr.kro.blog.myself.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityServiceImpl implements SecurityService{
    private static final String SECRET_KEY = "wejfkklwjefongrej";

    @Override
    public String createToken(String subject, long ttlMillis) {
        if (ttlMillis <= 0) {
            throw new RuntimeException("Expire time must be greater than Zero: [" + ttlMillis + "]");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key key = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setSubject(subject)
                .signWith(signatureAlgorithm, key)
                .setExpiration(new Date(System.currentTimeMillis() + ttlMillis))
                .compact();
    }

    @Override
    public String getSubject(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
