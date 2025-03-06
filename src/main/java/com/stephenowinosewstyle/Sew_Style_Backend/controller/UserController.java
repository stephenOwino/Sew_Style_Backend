package com.stephenowinosewstyle.Sew_Style_Backend.controller;


import com.stephenowinosewstyle.Sew_Style_Backend.dto.UserDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping
        public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
                UserDTO createdUser = userService.createUser(userDTO);
                return ResponseEntity.ok(createdUser);
        }

        @GetMapping("/{id}")
        public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
                UserDTO user = userService.getUserById(id);
                return ResponseEntity.ok(user);
        }
}
