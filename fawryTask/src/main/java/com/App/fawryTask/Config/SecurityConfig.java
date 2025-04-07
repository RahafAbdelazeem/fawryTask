package com.App.fawryTask.Config;

import com.App.fawryTask.Repsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;


@Configuration
@EnableWebSecurity
@ComponentScan("com.App.fawryTask")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;
     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(username -> {
             com.App.fawryTask.Entity.User user = userRepository.findByUserName(username);
             return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
         }).passwordEncoder(passwordEncoder());
     }
     @Override
    protected void configure(HttpSecurity http)throws Exception{
         http.csrf().disable()
             .authorizeRequests()
                 .antMatchers("/auth/**").permitAll()
                 .antMatchers("/admin/**").hasRole("ADMIN")
                 .anyRequest().authenticated();

     }
     @Bean
    public PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
     }
}
