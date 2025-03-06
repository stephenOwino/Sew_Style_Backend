package com.stephenowinosewstyle.Sew_Style_Backend.controller;


import com.stephenowinosewstyle.Sew_Style_Backend.dto.CommentDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

        @Autowired
        private CommentService commentService;

        @PostMapping
        public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
                CommentDTO createdComment = commentService.createComment(commentDTO);
                return ResponseEntity.ok(createdComment);
        }

        @GetMapping("/{id}")
        public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
                CommentDTO comment = commentService.getCommentById(id);
                return ResponseEntity.ok(comment);
        }
}