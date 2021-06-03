package de.martenl.fashiondan.admin.graphql;

import de.martenl.fashiondan.admin.domain.User;
import de.martenl.fashiondan.admin.service.TokenService;
import graphql.kickstart.tools.GraphQLResolver;

public class UserResolver implements GraphQLResolver<User> {

    private final TokenService tokenService;

    public UserResolver(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    //@PreAuthorize("isAuthenticated()")
    public String getToken(User user) {
        return tokenService.createTokenForUser(user);
    }
}
