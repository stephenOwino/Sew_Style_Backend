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
}
