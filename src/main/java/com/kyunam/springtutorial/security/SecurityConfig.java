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
	@Autowired
	DataSource dataSource;
	
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
            		.defaultSuccessUrl("/users/loginForm")
            		.failureUrl("/users/registerForm")
                .loginPage("/users/loginForm")
                .loginProcessingUrl("/users/login")
                .permitAll()
                .and()
            .logout()
            .permitAll();
    }
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		String query1 = "SELECT uid username, upw password, true enabled FROM member where uid = ?";
		String query2 = "SELECT member uid, role_name role FROM member_role WHERE member = ?";
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(query1)
		.rolePrefix("ROLE_")
		.authoritiesByUsernameQuery(query2);
	}
}