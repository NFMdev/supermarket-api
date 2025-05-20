package com.github.NFMdev.supermarket_api.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public JwtAuthenticationToken(String username) {
        super(username, null, AuthorityUtils.NO_AUTHORITIES);
    }

    public static void authenticate(String username) {
        JwtAuthenticationToken token = new JwtAuthenticationToken(username);
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
