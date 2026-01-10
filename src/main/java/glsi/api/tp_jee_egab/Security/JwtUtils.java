package glsi.api.tp_jee_egab.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expriation;

    public String generateToken(String email, String name) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expriation);

        return Jwts.builder()
                .setExpiration(expiryDate)
                .setSubject(email)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getEmailFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                .build()
                .parseClaimsJwt(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try{

            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJwt(token)
                    .getBody();

            return true;

        }catch (Exception e) {
            return false;
        }
    }

}
