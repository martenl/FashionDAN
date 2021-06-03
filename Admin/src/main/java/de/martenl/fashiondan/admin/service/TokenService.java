package de.martenl.fashiondan.admin.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import de.martenl.fashiondan.admin.domain.User;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class TokenService {


    private final Algorithm signingAlgorithm;
    private final String jwtIssuer;
    private final int tokenValidityTime;
    private final Clock clock;

    public TokenService(final Algorithm signingAlgorithm,
                        final String jwtIssuer,
                        final int tokenValidityTime,
                        final Clock clock) {

        this.signingAlgorithm = signingAlgorithm;
        this.jwtIssuer = jwtIssuer;
        this.tokenValidityTime = tokenValidityTime;
        this.clock = clock;
    }

    public String createTokenForUser(final User user) {
        final Instant now = Instant.now(clock);
        final Instant expirationTime = now.plus(Duration.ofHours(tokenValidityTime));
        return JWT.create()
                .withIssuer(jwtIssuer)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(expirationTime))
                .withSubject(user.getEmail())
                .sign(signingAlgorithm);
    }
}
