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
    String [] ACCEPTABLE_ENDPOINTS={"/login","/register","/startpage"};
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(ACCEPTABLE_ENDPOINTS).permitAll()
                .antMatchers("/","/*product*/**").access("hasRole('USER')").and().formLogin().loginPage("/login");
    }
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username,password,enabled from users where username=?")
                .authoritiesByUsernameQuery("select username,role from user_roles where username=?");
//        auth.inMemoryAuthentication().withUser("Medo").password("1234").roles("USER");
    }
}
