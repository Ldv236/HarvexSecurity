package com.harvex.HarvexSecurity.config;

import com.harvex.HarvexSecurity.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProviderImpl authProvider;

    @Autowired
    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }

    //there is configure auth
    protected void configure (AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }
}
