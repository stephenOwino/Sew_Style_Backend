package com.stephenowinosewstyle.Sew_Style_Backend.controller;

import com.stephenowinosewstyle.Sew_Style_Backend.dto.TailorDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.service.TailorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tailors")
public class TailorController {

        @Autowired
        private TailorService tailorService;

        @PostMapping
        public ResponseEntity<TailorDTO> createTailor(@RequestBody TailorDTO tailorDTO) {
                try {
                        TailorDTO createdTailor = tailorService.createTailor(tailorDTO);
                        return ResponseEntity.ok(createdTailor);
                } catch (RuntimeException e) {
                        return ResponseEntity.badRequest().body(null); // Or custom error DTO
                }
        }

        @GetMapping("/{id}")
        public ResponseEntity<TailorDTO> getTailorById(@PathVariable Long id) {
                try {
                        TailorDTO tailor = tailorService.getTailorById(id);
                        return ResponseEntity.ok(tailor);
                } catch (RuntimeException e) {
                        return ResponseEntity.notFound().build();
                }
        }

        @GetMapping("/by-user/{userId}")
        public ResponseEntity<TailorDTO> getTailorByUserId(@PathVariable Long userId) {
                try {
                        TailorDTO tailor = tailorService.getTailorByUserId(userId);
                        return ResponseEntity.ok(tailor);
                } catch (RuntimeException e) {
                        // Return 404 for "not found" instead of 500
                        return ResponseEntity.status(404).body(new TailorDTO(null, null, null, e.getMessage(), 0));
                } catch (Exception e) {
                        // Catch unexpected errors as 500 with message
                        return ResponseEntity.status(500).body(new TailorDTO(null, null, null, "Server error: " + e.getMessage(), 0));
                }
        }
}