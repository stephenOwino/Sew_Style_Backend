package com.stephenowinosewstyle.Sew_Style_Backend.service;

import com.stephenowinosewstyle.Sew_Style_Backend.dto.ImageDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.Gallery;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.Image;
import com.stephenowinosewstyle.Sew_Style_Backend.mapper.EntityMapper;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.GalleryRepository;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImagesService {

        private static final Logger logger = LoggerFactory.getLogger(ImagesService.class);

        @Autowired
        private ImageRepository imageRepository;

        @Autowired
        private GalleryRepository galleryRepository;

        @Autowired
        private EntityMapper entityMapper;

        @Transactional
        public ImageDTO createImage(ImageDTO imageDTO) {
                logger.debug("Creating image for galleryId: {}", imageDTO.getGalleryId());
                Gallery gallery = galleryRepository.findById(imageDTO.getGalleryId())
                        .orElseThrow(() -> {
                                logger.warn("Gallery not found with id: {}", imageDTO.getGalleryId());
                                return new IllegalArgumentException("Gallery not found with id: " + imageDTO.getGalleryId());
                        });
                Image image = entityMapper.imageDTOToImage(imageDTO);
                image.setGallery(gallery);
                logger.debug("Saving image to database: {}", image);
                Image savedImage = imageRepository.save(image);
                logger.info("Image saved successfully: {}", savedImage.getId());
                return entityMapper.imageToImageDTO(savedImage);
        }

        public ImageDTO getImageById(Long id) {
                logger.debug("Fetching image with id: {}", id);
                Image image = imageRepository.findById(id)
                        .orElseThrow(() -> {
                                logger.warn("Image not found with id: {}", id);
                                return new IllegalArgumentException("Image not found with id: " + id);
                        });
                return entityMapper.imageToImageDTO(image);
        }

        public List<ImageDTO> getAllImages() {
                logger.debug("Fetching all images");
                return imageRepository.findAll().stream()
                        .map(entityMapper::imageToImageDTO)
                        .collect(Collectors.toList());
        }

        public List<ImageDTO> getImagesByGalleryId(Long galleryId) {
                logger.debug("Fetching images for galleryId: {}", galleryId);
                return imageRepository.findByGalleryId(galleryId).stream()
                        .map(entityMapper::imageToImageDTO)
                        .collect(Collectors.toList());
        }
}