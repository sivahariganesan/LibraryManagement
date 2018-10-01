package com.library.modules.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    DataSource dataSource;
    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests().antMatchers("/home").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/listBooks").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/lendHistory").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/availablityCheck").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/addBooks").hasAuthority("ADMIN")
                .antMatchers("/addMembers").hasAuthority("LIBRARIAN")
                .and().formLogin().loginPage("/login").permitAll()
                .and().formLogin().successHandler(authSuccessHandler)
                .and().logout().permitAll();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception
    {
        authBuilder.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled from userdetails where username=?")
                .authoritiesByUsernameQuery("Select username,role from userdetails where username=?")
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}