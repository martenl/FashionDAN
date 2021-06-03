package de.martenl.fashiondan.admin.filter;

import de.martenl.fashiondan.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JWTFilter extends OncePerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(JWTFilter.class.getName());
    private final static String AUTHORIZATION_HEADER = "Authorization";
    private final static String AUTHORIZATION_HEADER_START = "Bearer ";

    private final UserService userService;

    public JWTFilter(final UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse,
            final FilterChain filterChain) throws ServletException, IOException {
        final var token = Optional.ofNullable(httpServletRequest.getHeader(AUTHORIZATION_HEADER))
                .map(s -> s.replace(AUTHORIZATION_HEADER_START, "")).orElse("nothing");
        LOGGER.info(token);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
