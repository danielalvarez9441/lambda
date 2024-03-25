package lambdafunction.utilidades;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {



    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken() {


        Date now = new Date();

        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, "vqsmU3TAt3UW4zb4Q9IExgHcqVHzmge1cTO8X4FKrq8JqoUcGwW1tZ8q3REgDWZlVjSBiPc1Yj8K7x4hMzFZLdQm1N67WPeOJCDkRvgOvuIVzTshEO4GLxjP3WgVhbP\n")
                .compact();
    }


}

