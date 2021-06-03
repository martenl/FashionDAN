package de.martenl.fashiondan.admin.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import de.martenl.fashiondan.admin.filter.JWTFilter;
import de.martenl.fashiondan.admin.service.TokenService;
import de.martenl.fashiondan.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class JWTConfig {

    @Autowired
    public Clock clock;
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.issuer}")
    private String jwtIssuer;
    @Value("${jwt.tokenValidityTime}")
    private int tokenValidityTime;

    @Bean
    public Algorithm jwtAlgorithm() {
        return Algorithm.HMAC256(jwtSecret);
    }

    @Bean
    public JWTVerifier verifier(Algorithm algorithm) {
        return JWT
                .require(algorithm)
                .withIssuer(jwtIssuer)
                .build();
    }

    @Bean
    public JWTFilter jwtFilter(UserService userService) {
        return new JWTFilter(userService);
    }

    @Bean
    public TokenService tokenService(final Algorithm jwtAlgorithm, final Clock clock) {
        return new TokenService(jwtAlgorithm, jwtIssuer, tokenValidityTime, clock);
    }
}
