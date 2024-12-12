package com.stephenowinosewstyle.Sew_Style_Backend.controller;


import com.stephenowinosewstyle.Sew_Style_Backend.entity.User;
import com.stephenowinosewstyle.Sew_Style_Backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
        private final UserService userService;

        @GetMapping
        public List<User> getUsers(){
                return userService.getUsers();
        }
}
