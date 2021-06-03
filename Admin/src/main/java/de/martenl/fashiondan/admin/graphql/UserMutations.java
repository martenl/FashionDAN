package de.martenl.fashiondan.admin.graphql;

import de.martenl.fashiondan.admin.domain.User;
import de.martenl.fashiondan.admin.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserMutations implements GraphQLMutationResolver {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserService userService;
    private final AuthenticationProvider authenticationProvider;

    public UserMutations(final UserService userService, final AuthenticationProvider authenticationProvider) {
        this.userService = userService;
        this.authenticationProvider = authenticationProvider;
    }

    public User login(final String email, final String password) {
        LOGGER.info(String.format("Logging %s in", email));
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(email, password);
        try {
            SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(credentials));
            LOGGER.info(SecurityContextHolder.getContext().getAuthentication().toString());
        } catch (Exception ex) {
            LOGGER.info(ex.getLocalizedMessage());

        }
        return (User) userService.loadUserByUsername(email);
    }

    public boolean logout(final String email) {
        LOGGER.info(String.format("Logging %s out", email));
        return true;
    }

    void createUser() {
    }

    void updateUser() {
    }

    void deleteUser() {
    }

    void updatePassword(User user, String oldPassword, String newPassword) {
    }
}
