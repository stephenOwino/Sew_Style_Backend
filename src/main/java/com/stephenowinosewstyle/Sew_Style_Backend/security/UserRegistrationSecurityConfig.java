package com.stephenowinosewstyle.Sew_Style_Backend.security;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

@EnableWebSecurity
@Service
public class UserRegistrationSecurityConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder(11);
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                        .csrf(customizer -> customizer.disable())
                        .authorizeHttpRequests(request -> request
                                .requestMatchers("/auth/**")
                                .permitAll()
                                .anyRequest().authenticated())
                        // http.formLogin(Customizer.withDefaults());
                        .httpBasic(Customizer.withDefaults())//to avoid returning form while testing in postman(rest clients)
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//making http stateless
                        //to generate a new session id everytime the user makes a request
                        //.addFilterBefore( jwtFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
        }
}

