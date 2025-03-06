package com.stephenowinosewstyle.Sew_Style_Backend.service;


import com.stephenowinosewstyle.Sew_Style_Backend.dto.ImageDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.Image;
import com.stephenowinosewstyle.Sew_Style_Backend.mapper.EntityMapper;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagesService {

        @Autowired
        private ImageRepository imageRepository;

        @Autowired
        private EntityMapper entityMapper;

        public ImageDTO createImage(ImageDTO imageDTO) {
                Image image = entityMapper.imageDTOToImage(imageDTO);
                image = imageRepository.save(image);
                return entityMapper.imageToImageDTO(image);
        }

        public ImageDTO getImageById(Long id) {
                Image image = imageRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Image not found"));
                return entityMapper.imageToImageDTO(image);
        }
}