package com.stephenowinosewstyle.Sew_Style_Backend.controller;

import com.stephenowinosewstyle.Sew_Style_Backend.dto.GalleryDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/galleries")
public class GalleryController {

        @Autowired
        private GalleryService galleryService;

        @PostMapping
        public ResponseEntity<GalleryDTO> createGallery(@RequestBody GalleryDTO galleryDTO) {
                GalleryDTO createdGallery = galleryService.createGallery(galleryDTO);
                return ResponseEntity.ok(createdGallery);
        }

        @GetMapping("/{id}")
        public ResponseEntity<GalleryDTO> getGalleryById(@PathVariable Long id) {
                GalleryDTO gallery = galleryService.getGalleryById(id);
                return ResponseEntity.ok(gallery);
        }
}