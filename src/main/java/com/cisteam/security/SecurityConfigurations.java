package com.cisteam.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

     @Autowired
     DataSource dataSource;
     String [] ACCEPTABLE_ENDPOINTS={"/startAdmin","/start"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(ACCEPTABLE_ENDPOINTS).permitAll()
                .antMatchers("/","/*-admin").access("hasRole('ADMIN')")
                .antMatchers("/" , "/*product*/**" ).access("hasRole('USER')")
                .and().formLogin();
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled from users where username=?")
                .authoritiesByUsernameQuery("select username,role from user_roles where username=?");
    }

}
