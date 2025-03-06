package com.stephenowinosewstyle.Sew_Style_Backend.service;

import com.stephenowinosewstyle.Sew_Style_Backend.dto.CommentDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.Comment;
import com.stephenowinosewstyle.Sew_Style_Backend.mapper.EntityMapper;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

        @Autowired
        private CommentRepository commentRepository;

        @Autowired
        private EntityMapper entityMapper;

        public CommentDTO createComment(CommentDTO commentDTO) {
                Comment comment = entityMapper.commentDTOToComment(commentDTO);
                comment = commentRepository.save(comment);
                return entityMapper.commentToCommentDTO(comment);
        }

        public CommentDTO getCommentById(Long id) {
                Comment comment = commentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Comment not found"));
                return entityMapper.commentToCommentDTO(comment);
        }
}