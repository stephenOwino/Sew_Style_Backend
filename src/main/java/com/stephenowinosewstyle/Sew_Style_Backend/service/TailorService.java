package com.stephenowinosewstyle.Sew_Style_Backend.service;


import com.stephenowinosewstyle.Sew_Style_Backend.dto.TailorDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.Tailor;
import com.stephenowinosewstyle.Sew_Style_Backend.mapper.EntityMapper;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.TailorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TailorService {

        @Autowired
        private TailorRepository tailorRepository;

        @Autowired
        private EntityMapper entityMapper;

        public TailorDTO createTailor(TailorDTO tailorDTO) {
                Tailor tailor = entityMapper.tailorDTOToTailor(tailorDTO);
                tailor = tailorRepository.save(tailor);
                return entityMapper.tailorToTailorDTO(tailor);
        }

        public TailorDTO getTailorById(Long id) {
                Tailor tailor = tailorRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Tailor not found"));
                return entityMapper.tailorToTailorDTO(tailor);
        }
}
