package com.stephenowinosewstyle.Sew_Style_Backend.service;

import com.stephenowinosewstyle.Sew_Style_Backend.dto.TailorDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.Tailor;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.User;
import com.stephenowinosewstyle.Sew_Style_Backend.mapper.EntityMapper;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.TailorRepository;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.UserRepository;
import com.stephenowinosewstyle.Sew_Style_Backend.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TailorService {

        @Autowired
        private TailorRepository tailorRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private EntityMapper entityMapper;

        public TailorDTO createTailor(TailorDTO tailorDTO) {
                // Fetch or validate the user by email (assumes user is already registered)
                User user = userRepository.findByEmail(tailorDTO.getUser().getEmail())
                        .orElseThrow(() -> new RuntimeException("User not found with email: " + tailorDTO.getUser().getEmail()));

                // Ensure the user has TAILOR role
                if (!user.getRole().equals(Role.TAILOR)) {
                        throw new RuntimeException("User must have TAILOR role to be a tailor");
                }

                // Map DTO to entity using EntityMapper
                Tailor tailor = entityMapper.tailorDTOToTailor(tailorDTO);
                tailor.setUser(user); // Ensure the existing user is linked

                // Add missing fields from DTO (since your mapper doesn’t handle these yet)
                tailor.setBio(tailorDTO.getBio());
                tailor.setSpecialization(tailorDTO.getSpecialization());
                tailor.setExperienceYears(tailorDTO.getExperienceYears());

                // Save and map back to DTO
                Tailor savedTailor = tailorRepository.save(tailor);
                return entityMapper.tailorToTailorDTO(savedTailor);
        }

        public TailorDTO getTailorById(Long id) {
                Tailor tailor = tailorRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Tailor not found with id: " + id));
                return entityMapper.tailorToTailorDTO(tailor);
        }
}