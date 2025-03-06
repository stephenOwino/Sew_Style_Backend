package com.stephenowinosewstyle.Sew_Style_Backend.service;

import com.stephenowinosewstyle.Sew_Style_Backend.dto.GalleryDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.Gallery;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.Tailor;
import com.stephenowinosewstyle.Sew_Style_Backend.mapper.EntityMapper;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.GalleryRepository;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.TailorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GalleryService {

        @Autowired
        private GalleryRepository galleryRepository;

        @Autowired
        private TailorRepository tailorRepository;

        @Autowired
        private EntityMapper entityMapper;

        @Transactional
        public GalleryDTO createGallery(GalleryDTO galleryDTO) {
                if (galleryDTO.getTailorId() == null || galleryDTO.getTitle() == null || galleryDTO.getTitle().trim().isEmpty()) {
                        throw new IllegalArgumentException("Tailor ID and title are required");
                }
                Tailor tailor = tailorRepository.findById(galleryDTO.getTailorId())
                        .orElseThrow(() -> new IllegalArgumentException("Tailor not found with id: " + galleryDTO.getTailorId()));
                Gallery gallery = entityMapper.galleryDTOToGallery(galleryDTO);
                gallery.setTailor(tailor);
                Gallery savedGallery = galleryRepository.save(gallery);
                return entityMapper.galleryToGalleryDTO(savedGallery);
        }

        public GalleryDTO getGalleryById(Long id) {
                Gallery gallery = galleryRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Gallery not found with id: " + id));
                return entityMapper.galleryToGalleryDTO(gallery);
        }
}