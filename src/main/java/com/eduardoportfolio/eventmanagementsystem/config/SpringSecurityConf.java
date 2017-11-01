package com.eduardoportfolio.eventmanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Eduardo on 01/11/17.
 */
@Configuration
public class SpringSecurityConf extends WebSecurityConfigurerAdapter{

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN", "USER")
                .and().withUser("user").password("user").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/events/**", "/lectures/**").permitAll()
                .and().authorizeRequests().antMatchers("/login", "/logout").permitAll()
                .and().authorizeRequests().antMatchers("/static/css/**", "/static/js/**", "/static/images/**",
                "/**/favicon.ico").permitAll()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
                .and().logout()
                    .deleteCookies("remove")
                    .invalidateHttpSession(true)
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/logout-success")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}
