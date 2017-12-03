package com.rabigol.kswallet.config;

import com.rabigol.kswallet.service.AppBasicAuthenticationEntryPoint;
import com.rabigol.kswallet.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.rabigol.kswallet.constants.AppConstants.REALM_NAME;
import static com.rabigol.kswallet.constants.UserRoles.ROLE_ADMIN;
import static com.rabigol.kswallet.constants.UserRoles.ROLE_USER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private AppBasicAuthenticationEntryPoint appBasicAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/user/**")
                .hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .and().httpBasic().realmName(REALM_NAME).authenticationEntryPoint(appBasicAuthenticationEntryPoint);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder);
    }
}
