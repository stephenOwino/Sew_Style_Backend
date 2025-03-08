package com.stephenowinosewstyle.Sew_Style_Backend.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {

        private static final Logger logger = LoggerFactory.getLogger(FileController.class);

        @Value("${file.upload-dir:/home/stephen/Sew_Style/uploads}")
        private String baseUploadDir;

        @GetMapping("/uploads/{tailorId}/{filename:.+}")
        public ResponseEntity<Resource> serveImage(
                @PathVariable String tailorId,
                @PathVariable String filename) {
                try {
                        Path filePath = Paths.get(baseUploadDir, tailorId, filename).normalize();
                        logger.info("Attempting to serve file: {}", filePath);
                        Resource resource = new UrlResource(filePath.toUri());
                        if (resource.exists() && resource.isReadable()) {
                                String contentType = filename.endsWith(".png") ? "image/png" : "image/jpeg";
                                logger.debug("Serving file with content type: {}", contentType);
                                return ResponseEntity.ok()
                                        .contentType(MediaType.parseMediaType(contentType))
                                        .body(resource);
                        } else {
                                logger.warn("File not found or not readable: {}", filePath);
                                return ResponseEntity.notFound().build();
                        }
                } catch (Exception e) {
                        logger.error("Error serving image: {}/{}", tailorId, filename, e);
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
        }

        @GetMapping("/uploads/{filename:.+}")
        public ResponseEntity<Resource> serveImageLegacy(
                @PathVariable String filename) {
                try {
                        Path filePath = Paths.get(baseUploadDir, filename).normalize();
                        logger.info("Attempting to serve legacy file: {}", filePath);
                        Resource resource = new UrlResource(filePath.toUri());
                        if (resource.exists() && resource.isReadable()) {
                                String contentType = filename.endsWith(".png") ? "image/png" : "image/jpeg";
                                logger.debug("Serving legacy file with content type: {}", contentType);
                                return ResponseEntity.ok()
                                        .contentType(MediaType.parseMediaType(contentType))
                                        .body(resource);
                        } else {
                                logger.warn("Legacy file not found or not readable: {}", filePath);
                                return ResponseEntity.notFound().build();
                        }
                } catch (Exception e) {
                        logger.error("Error serving legacy image: {}", filename, e);
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
        }
}
