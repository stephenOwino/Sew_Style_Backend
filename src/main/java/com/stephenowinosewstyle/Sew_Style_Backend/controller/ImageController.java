package com.stephenowinosewstyle.Sew_Style_Backend.controller;

import com.stephenowinosewstyle.Sew_Style_Backend.dto.ImageDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class ImageController {

        @Autowired
        private ImagesService imageService;

        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<ImageDTO> createImage(
                @RequestParam("image") MultipartFile imageFile,
                @RequestParam("galleryId") Long galleryId) {
                try {
                        // Validate file
                        if (imageFile.isEmpty()) {
                                return ResponseEntity.badRequest().body(new ImageDTO(null, "No file uploaded", 0, null, null));
                        }
                        String originalFilename = imageFile.getOriginalFilename();
                        if (originalFilename == null || !originalFilename.matches(".*\\.(jpg|jpeg|png)")) {
                                return ResponseEntity.badRequest().body(new ImageDTO(null, "Only JPG/JPEG/PNG allowed", 0, null, null));
                        }
                        if (imageFile.getSize() > 5 * 1024 * 1024) {
                                return ResponseEntity.badRequest().body(new ImageDTO(null, "File exceeds 5MB limit", 0, null, null));
                        }

                        // Save file
                        String uploadDir = "uploads/";
                        Path uploadPath = Paths.get(uploadDir);
                        if (!Files.exists(uploadPath)) {
                                Files.createDirectories(uploadPath);
                        }
                        String fileName = UUID.randomUUID() + "_" + originalFilename;
                        Path filePath = uploadPath.resolve(fileName);
                        Files.write(filePath, imageFile.getBytes());

                        // Prepare DTO
                        ImageDTO newImageDTO = new ImageDTO();
                        newImageDTO.setImageUrl("/uploads/" + fileName); // Relative URL for static serving
                        newImageDTO.setLikeCount(0);
                        newImageDTO.setGalleryId(galleryId);

                        // Save to DB via service
                        ImageDTO createdImage = imageService.createImage(newImageDTO);
                        return ResponseEntity.ok(createdImage);
                } catch (IOException e) {
                        return ResponseEntity.status(500).body(new ImageDTO(null, "Failed to upload image: " + e.getMessage(), 0, null, null));
                } catch (IllegalArgumentException e) {
                        return ResponseEntity.badRequest().body(new ImageDTO(null, e.getMessage(), 0, null, null));
                }
        }

        @GetMapping("/{id}")
        public ResponseEntity<ImageDTO> getImageById(@PathVariable Long id) {
                try {
                        ImageDTO image = imageService.getImageById(id);
                        return ResponseEntity.ok(image);
                } catch (IllegalArgumentException e) {
                        return ResponseEntity.notFound().build();
                }
        }
}