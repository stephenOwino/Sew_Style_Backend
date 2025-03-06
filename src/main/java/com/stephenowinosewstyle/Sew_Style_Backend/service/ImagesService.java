package com.stephenowinosewstyle.Sew_Style_Backend.service;

import com.stephenowinosewstyle.Sew_Style_Backend.dto.ImageDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.Gallery;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.Image;
import com.stephenowinosewstyle.Sew_Style_Backend.mapper.EntityMapper;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.GalleryRepository;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImagesService {

        @Autowired
        private ImageRepository imageRepository;

        @Autowired
        private GalleryRepository galleryRepository;

        @Autowired
        private EntityMapper entityMapper;

        @Transactional
        public ImageDTO createImage(ImageDTO imageDTO) {
                Gallery gallery = galleryRepository.findById(imageDTO.getGalleryId())
                        .orElseThrow(() -> new IllegalArgumentException("Gallery not found with id: " + imageDTO.getGalleryId()));

                Image image = entityMapper.imageDTOToImage(imageDTO);
                image.setGallery(gallery);
                Image savedImage = imageRepository.save(image);
                return entityMapper.imageToImageDTO(savedImage);
        }

        public ImageDTO getImageById(Long id) {
                Image image = imageRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Image not found with id: " + id));
                return entityMapper.imageToImageDTO(image);
        }
}