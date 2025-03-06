package com.stephenowinosewstyle.Sew_Style_Backend.security;

import com.stephenowinosewstyle.Sew_Style_Backend.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

        @Autowired
        private UserDetailsService userDetailsService; // Your custom UserDetailsService

        @Autowired
        private JwtRequestFilter jwtRequestFilter; // JWT filter for token validation

        // PasswordEncoder Bean
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder(12); // Strong encryption with strength 12
        }

        // AuthenticationProvider Bean
        @Bean
        public AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setPasswordEncoder(passwordEncoder());
                provider.setUserDetailsService(userDetailsService);
                return provider;
        }

        // AuthenticationManager Bean
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
                return config.getAuthenticationManager();
        }

        // SecurityFilterChain Bean
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                        .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless REST API
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless for JWT
                        .authorizeHttpRequests(auth -> auth
                                // Public endpoints
                                .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                                // Admin-only endpoints
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                // Tailor-only endpoints
                                .requestMatchers("/api/tailor/**").hasRole("TAILOR")
                                // User-accessible endpoints (e.g., viewing galleries)
                                .requestMatchers("/api/gallery/**").hasAnyRole("USER", "TAILOR", "ADMIN")
                                // All other endpoints require authentication
                                .anyRequest().authenticated()
                        )
                        .authenticationProvider(authenticationProvider()) // Use custom provider
                        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter
                        .build();
        }

        // CORS Configuration for frontend (React)
        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // Adjust for your React frontend URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
        }
}

