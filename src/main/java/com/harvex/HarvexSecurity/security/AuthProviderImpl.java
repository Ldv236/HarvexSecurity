package com.harvex.HarvexSecurity.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true; //for defenition which authProvider is using //now we have only one AuthProv, so always return true
    }
}