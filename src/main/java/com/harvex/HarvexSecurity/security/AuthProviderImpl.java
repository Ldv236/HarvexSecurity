package com.harvex.HarvexSecurity.security;

import com.harvex.HarvexSecurity.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final PersonDetailsService personDetailsService;
    @Autowired
    public AuthProviderImpl(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails personDetails = personDetailsService.loadUserByUsername(username);
        String password  = authentication.getCredentials().toString();
        if (!password.equals(personDetails.getPassword()))
            throw new BadCredentialsException("Wrong password");
        else
            return new UsernamePasswordAuthenticationToken(personDetails, password,
                    Collections.emptyList()); //to specified roles of different users
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true; //for defenition which authProvider is using //now we have only one AuthProv, so always return true
    }
}
