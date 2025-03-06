package com.stephenowinosewstyle.Sew_Style_Backend.controller;

import com.stephenowinosewstyle.Sew_Style_Backend.dto.ImageDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")
public class ImageController {

        @Autowired
        private ImagesService imageService;

        @PostMapping
        public ResponseEntity<ImageDTO> createImage(@RequestBody ImageDTO imageDTO) {
                ImageDTO createdImage = imageService.createImage(imageDTO);
                return ResponseEntity.ok(createdImage);
        }

        @GetMapping("/{id}")
        public ResponseEntity<ImageDTO> getImageById(@PathVariable Long id) {
                ImageDTO image = imageService.getImageById(id);
                return ResponseEntity.ok(image);
        }
}