package com.harvex.HarvexSecurity.config;

import com.harvex.HarvexSecurity.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder; for no-crypt passwords
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*private final AuthProviderImpl authProvider;

    @Autowired
    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }*/

    private final PersonDetailsService personDetailsService; //default SprSec usage check username &password
    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //to configure SprSec as is; + config authorization (auth yes/no, roles)
        //http.csrf().disable() //off defend cross_site_request
        http
                .authorizeRequests()
                .antMatchers("/auth/login", "/auth/registration", "/error").permitAll()
                .anyRequest().authenticated()
                .and()                                      //this separate (devide) different logical settings
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login");
    }

    //here we configuring auth
    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        //auth.authenticationProvider(authProvider);
        auth.userDetailsService(personDetailsService)
                    .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        //return NoOpPasswordEncoder.getInstance(); //for now without encryption. Will make later
        return new BCryptPasswordEncoder();
    }
}
