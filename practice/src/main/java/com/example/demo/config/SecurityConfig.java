package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          .authorizeHttpRequests(auth -> auth
             
              .requestMatchers("/admin/signin", "/admin/signup", "/css/**", "/js/**", "/images/**").permitAll()
              
              .requestMatchers("/admin/**").authenticated()
              .anyRequest().permitAll()
          )
          .formLogin(login -> login
              .loginPage("/admin/signin")           
              .loginProcessingUrl("/admin/signin")  
              .defaultSuccessUrl("/admin/contacts", true)
              .failureUrl("/admin/signin?error")
              .permitAll()
          )
          .logout(logout -> logout
              .logoutUrl("/admin/logout")
              .logoutSuccessUrl("/admin/signin?logout")
              .invalidateHttpSession(true)
              .deleteCookies("JSESSIONID")
          );

        return http.build();
    }
}

