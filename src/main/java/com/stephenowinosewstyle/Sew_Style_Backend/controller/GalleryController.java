package com.stephenowinosewstyle.Sew_Style_Backend.controller;

import com.stephenowinosewstyle.Sew_Style_Backend.dto.GalleryDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.service.GalleryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/galleries")
public class GalleryController {

        @Autowired
        private GalleryService galleryService;

        @PostMapping
        public ResponseEntity<GalleryDTO> createGallery(@Valid @RequestBody GalleryDTO galleryDTO) {
                try {
                        GalleryDTO createdGallery = galleryService.createGallery(galleryDTO);
                        return ResponseEntity.ok(createdGallery);
                } catch (IllegalArgumentException e) {
                        return ResponseEntity.badRequest().body(null);
                } catch (Exception e) {
                        return ResponseEntity.status(500).body(null);
                }
        }

        @GetMapping("/{id}")
        public ResponseEntity<GalleryDTO> getGalleryById(@PathVariable Long id) {
                try {
                        GalleryDTO gallery = galleryService.getGalleryById(id);
                        return ResponseEntity.ok(gallery);
                } catch (IllegalArgumentException e) {
                        return ResponseEntity.notFound().build();
                } catch (Exception e) {
                        return ResponseEntity.status(500).body(null);
                }
        }

        @GetMapping
        public ResponseEntity<List<GalleryDTO>> getAllGalleries() {
                try {
                        List<GalleryDTO> galleries = galleryService.getAllGalleries();
                        return ResponseEntity.ok(galleries);
                } catch (Exception e) {
                        return ResponseEntity.status(500).body(null);
                }
        }


}