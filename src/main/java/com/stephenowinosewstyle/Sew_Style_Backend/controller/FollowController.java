package com.stephenowinosewstyle.Sew_Style_Backend.controller;



import com.stephenowinosewstyle.Sew_Style_Backend.dto.FollowDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follows")
public class FollowController {

        @Autowired
        private FollowService followService;

        @PostMapping
        public ResponseEntity<FollowDTO> createFollow(@RequestBody FollowDTO followDTO) {
                FollowDTO createdFollow = followService.createFollow(followDTO);
                return ResponseEntity.ok(createdFollow);
        }

        @GetMapping("/{id}")
        public ResponseEntity<FollowDTO> getFollowById(@PathVariable Long id) {
                FollowDTO follow = followService.getFollowById(id);
                return ResponseEntity.ok(follow);
        }
}
