package com.kyunam.springtutorial.security;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


import groovy.util.logging.Log;

@Log
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/resources/**", "/webjars/**").permitAll();
		
        http
            .authorizeRequests()
                .antMatchers("/", "/users/registerForm", "/users/**", "/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
            		.defaultSuccessUrl("/users")
            		.failureUrl("/users/registerForm")
                .loginPage("/users/loginForm")
                .permitAll()
                .and()
            .logout()
            .permitAll();
    }
}