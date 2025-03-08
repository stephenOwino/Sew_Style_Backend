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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryService {

        private static final int MAX_GALLERIES_PER_TAILOR = 10; // New: Define limit

        @Autowired
        private GalleryRepository galleryRepository;

        @Autowired
        private TailorRepository tailorRepository;

        @Autowired
        private EntityMapper entityMapper;

        @Transactional
        public GalleryDTO createGallery(GalleryDTO galleryDTO) {
                // Updated validation: Add skill as required
                if (galleryDTO.getTailorId() == null || galleryDTO.getTitle() == null || galleryDTO.getTitle().trim().isEmpty() ||
                        galleryDTO.getSkill() == null || galleryDTO.getSkill().trim().isEmpty()) {
                        throw new IllegalArgumentException("Tailor ID, title, and skill are required");
                }

                // Fetch tailor
                Tailor tailor = tailorRepository.findById(galleryDTO.getTailorId())
                        .orElseThrow(() -> new IllegalArgumentException("Tailor not found with id: " + galleryDTO.getTailorId()));

                // New: Check gallery limit
                long galleryCount = galleryRepository.countByTailorId(galleryDTO.getTailorId());
                if (galleryCount >= MAX_GALLERIES_PER_TAILOR) {
                        throw new IllegalArgumentException("Maximum galleries reached (" + MAX_GALLERIES_PER_TAILOR + ") for tailor ID: " + galleryDTO.getTailorId());
                }

                // Map and save gallery
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

        public List<GalleryDTO> getAllGalleries() {
                List<Gallery> galleries = galleryRepository.findAll();
                return galleries.stream()
                        .map(entityMapper::galleryToGalleryDTO)
                        .collect(Collectors.toList());
        }
}