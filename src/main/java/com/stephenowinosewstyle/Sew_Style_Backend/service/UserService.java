package com.stephenowinosewstyle.Sew_Style_Backend.service;

import com.stephenowinosewstyle.Sew_Style_Backend.dto.UserDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.User;
import com.stephenowinosewstyle.Sew_Style_Backend.mapper.EntityMapper;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private EntityMapper entityMapper;

        public UserDTO createUser(UserDTO userDTO) {
                User user = entityMapper.userDTOToUser(userDTO);
                user = userRepository.save(user);
                return entityMapper.userToUserDTO(user);
        }

        public UserDTO getUserById(Long id) {
                User user = userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                return entityMapper.userToUserDTO(user);
        }
}

