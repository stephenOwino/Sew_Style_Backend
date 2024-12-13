package com.stephenowinosewstyle.Sew_Style_Backend.controller;

import com.stephenowinosewstyle.Sew_Style_Backend.RegistrationRequest;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.User;
import com.stephenowinosewstyle.Sew_Style_Backend.event.RegistrationCompleteEvent;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.VerificationTokenRepository;
import com.stephenowinosewstyle.Sew_Style_Backend.service.UserService;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.VerificationToken;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

        private final UserService userService;
        private final ApplicationEventPublisher publisher;
        private final VerificationTokenRepository tokenRepository;

        @PostMapping
        public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){
                User user = userService.registerUser(registrationRequest);
                publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
                return "Success!  Please, check your email for to complete your registration";
        }

        @GetMapping("/verifyEmail")
        public String verifyEmail(@RequestParam("token") String token){
                VerificationToken theToken = tokenRepository.findByToken(token);
                if (theToken.getUser().isEnabled()){
                        return "This account has already been verified, please, login.";
                }
                String verificationResult = userService.validateToken(token);
                if (verificationResult.equalsIgnoreCase("valid")){
                        return "Email verified successfully. Now you can login to your account";
                }
                return "Invalid verification token";
        }



        public String applicationUrl(HttpServletRequest request) {
                return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        }
}