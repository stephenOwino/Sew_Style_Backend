package com.stephenowinosewstyle.Sew_Style_Backend.service;


import com.stephenowinosewstyle.Sew_Style_Backend.dto.GalleryDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.Gallery;
import com.stephenowinosewstyle.Sew_Style_Backend.mapper.EntityMapper;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {

        @Autowired
        private GalleryRepository galleryRepository;

        @Autowired
        private EntityMapper entityMapper;

        public GalleryDTO createGallery(GalleryDTO galleryDTO) {
                Gallery gallery = entityMapper.galleryDTOToGallery(galleryDTO);
                gallery = galleryRepository.save(gallery);
                return entityMapper.galleryToGalleryDTO(gallery);
        }

        public GalleryDTO getGalleryById(Long id) {
                Gallery gallery = galleryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Gallery not found"));
                return entityMapper.galleryToGalleryDTO(gallery);
        }
}
