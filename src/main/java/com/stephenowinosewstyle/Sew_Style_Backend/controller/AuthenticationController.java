package com.stephenowinosewstyle.Sew_Style_Backend.controller;

import com.stephenowinosewstyle.Sew_Style_Backend.RegistrationRequest;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.User;
import com.stephenowinosewstyle.Sew_Style_Backend.jwt.JWTService;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.UserRepository;
import com.stephenowinosewstyle.Sew_Style_Backend.request.AuthenticationRequest;
import com.stephenowinosewstyle.Sew_Style_Backend.response.AuthenticationResponse;
import com.stephenowinosewstyle.Sew_Style_Backend.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserDetailsService userDetailsService;

        @Autowired
        private JWTService jwtService;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;


        // ... (autowired fields unchanged)

        // ... (imports unchanged)
        @PostMapping("/register")
        public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request) {
                if (userRepository.findByEmail(request.email()).isPresent()) {
                        return ResponseEntity.badRequest().body(new AuthenticationResponse("Email already in use"));
                }

                User user = new User();
                user.setFirstName(request.firstName());
                user.setLastName(request.lastName());
                user.setEmail(request.email());
                user.setPassword(passwordEncoder.encode(request.password()));
                Role role = Role.valueOf(request.role().toUpperCase());
                user.setRole(role);
                user.setEnabled(true);

                User savedUser = userRepository.save(user); // Save to get ID

                UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
                String token = jwtService.generateToken(userDetails);

                return ResponseEntity.ok(new AuthenticationResponse(token, request.firstName(), role.name(), savedUser.getId()));
        }

        @PostMapping("/login")
        public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.email(), request.password())
                );

                UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
                String token = jwtService.generateToken(userDetails);
                User user = userRepository.findByEmail(request.email())
                        .orElseThrow(() -> new RuntimeException("User not found"));

                return ResponseEntity.ok(new AuthenticationResponse(token, user.getFirstName(), user.getRole().name(), user.getId()));
        }
}