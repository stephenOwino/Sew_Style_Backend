package com.stephenowinosewstyle.Sew_Style_Backend.service;

import com.stephenowinosewstyle.Sew_Style_Backend.RegistrationRequest;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
        List<User> getUsers();
        User registerUser(RegistrationRequest request);
        Optional<User> findByEmail(String email);

        void saveUserVerificationToken(User theUser, String verificationToken);

        String validateToken(String theToken);
}