package com.riri.displayresume.security;

import com.riri.displayresume.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private SSUserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUserDetailsService(userRepository);
    }

    /*"/addpersonal","/postpersonal","/addeducation","/posteducation","/addexperience",
        "/postexperience", "/postskill","/addsummary","/addreference","/addview","/coverletter",*/
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .authorizeRequests()
                .antMatchers("/h2-console/**", "/css/**", "/images/**","/register").permitAll()
/*
                .antMatchers("/","/employerindex","/printletteremployer","/displayresumeemployer")
*/              .antMatchers("/addjob", "/postskill", "/addskill").hasAuthority("RECRUITER")
                .antMatchers( "/postskill", "/addskill").hasAuthority("EMPLOYER")
                .antMatchers("/","/printletter","/displayresume")
                .access("hasAuthority('USER') or hasAuthority('ADMIN')")
                .antMatchers("/employerindex","/index","/addpersonal","/postpersonal","/addeducation","/posteducation","/addexperience",
                        "/postexperience", "/addskill","/postskill","/addsummary","/addreference","/addview","/coverletter").access("hasAuthority('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll().permitAll()
                .and()
                .httpBasic();
        http
                .csrf().disable();
        http
                .headers().frameOptions().disable();



    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception{
       /* auth.inMemoryAuthentication()
                .withUser("user").password("password").authorities("USER")
                .and()
                .withUser("davewolf").password("beastmaster").authorities("ADMIN");*/
        auth
                .userDetailsService(userDetailsServiceBean());
    }

}
