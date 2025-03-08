package com.stephenowinosewstyle.Sew_Style_Backend.controller;

import com.stephenowinosewstyle.Sew_Style_Backend.dto.ImageDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.service.ImagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class ImageController {

        private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

        @Autowired
        private ImagesService imageService;

        @Value("${file.upload-dir:/home/stephen/Sew_Style/uploads}")
        private String baseUploadDir;

        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<ImageDTO> createImage(
                @RequestParam("image") MultipartFile imageFile,
                @RequestParam("galleryId") Long galleryId,
                @RequestParam("tailorId") Long tailorId) {
                try {
                        logger.debug("Starting image upload for galleryId: {}, tailorId: {}", galleryId, tailorId);

                        // Validation checks
                        if (imageFile.isEmpty()) {
                                logger.warn("No file uploaded");
                                return ResponseEntity.badRequest().body(new ImageDTO(null, "No file uploaded", 0, null, null));
                        }
                        String originalFilename = imageFile.getOriginalFilename();
                        if (originalFilename == null || !originalFilename.matches(".*\\.(jpg|jpeg|png)")) {
                                logger.warn("Invalid file type: {}", originalFilename);
                                return ResponseEntity.badRequest().body(new ImageDTO(null, "Only JPG/JPEG/PNG allowed", 0, null, null));
                        }
                        if (imageFile.getSize() > 10 * 1024 * 1024) { // 10MB
                                logger.warn("File too large: {} bytes", imageFile.getSize());
                                return ResponseEntity.badRequest().body(new ImageDTO(null, "File exceeds 10MB limit", 0, null, null));
                        }

                        // File saving logic
                        String uploadDir = baseUploadDir + "/" + tailorId;
                        Path uploadPath = Paths.get(uploadDir);
                        if (!Files.exists(uploadPath)) {
                                logger.debug("Creating directory: {}", uploadPath);
                                Files.createDirectories(uploadPath);
                        }
                        String fileName = UUID.randomUUID() + "_" + originalFilename;
                        Path filePath = uploadPath.resolve(fileName);
                        logger.debug("Saving file to: {}", filePath);
                        Files.write(filePath, imageFile.getBytes());

                        // DTO creation and service call
                        ImageDTO newImageDTO = new ImageDTO();
                        newImageDTO.setImageUrl("/uploads/" + tailorId + "/" + fileName);
                        newImageDTO.setLikeCount(0);
                        newImageDTO.setGalleryId(galleryId);
                        newImageDTO.setUploadedAt(LocalDateTime.now());

                        logger.debug("Persisting image metadata: {}", newImageDTO);
                        ImageDTO createdImage = imageService.createImage(newImageDTO);
                        logger.info("Image uploaded successfully: {}", createdImage);
                        return ResponseEntity.ok(createdImage);

                } catch (IOException e) {
                        logger.error("Failed to save file to filesystem: {}", e.getMessage(), e);
                        return ResponseEntity.status(500).body(new ImageDTO(null, "Failed to upload file: " + e.getMessage(), 0, null, null));
                } catch (IllegalArgumentException e) {
                        logger.warn("Invalid argument: {}", e.getMessage());
                        return ResponseEntity.badRequest().body(new ImageDTO(null, e.getMessage(), 0, null, null));
                } catch (Exception e) {
                        logger.error("Unexpected error during image upload: {}", e.getMessage(), e);
                        return ResponseEntity.status(500).body(new ImageDTO(null, "Unexpected error: " + e.getMessage(), 0, null, null));
                }
        }

        @GetMapping("/{id}")
        public ResponseEntity<ImageDTO> getImageById(@PathVariable Long id) {
                try {
                        logger.debug("Fetching image with id: {}", id);
                        ImageDTO image = imageService.getImageById(id);
                        return ResponseEntity.ok(image);
                } catch (IllegalArgumentException e) {
                        logger.warn("Image not found: {}", e.getMessage());
                        return ResponseEntity.notFound().build();
                }
        }

        @GetMapping
        public ResponseEntity<List<ImageDTO>> getAllImages(
                @RequestParam(value = "galleryId", required = false) Long galleryId) {
                logger.debug("Fetching images, galleryId: {}", galleryId);
                List<ImageDTO> images = (galleryId != null)
                        ? imageService.getImagesByGalleryId(galleryId)
                        : imageService.getAllImages();
                return ResponseEntity.ok(images);
        }
}