package com.stephenowinosewstyle.Sew_Style_Backend.service;

import com.stephenowinosewstyle.Sew_Style_Backend.dto.FollowDTO;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.Follow;
import com.stephenowinosewstyle.Sew_Style_Backend.mapper.EntityMapper;
import com.stephenowinosewstyle.Sew_Style_Backend.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

        @Autowired
        private FollowRepository followRepository;

        @Autowired
        private EntityMapper entityMapper;

        public FollowDTO createFollow(FollowDTO followDTO) {
                Follow follow = entityMapper.followDTOToFollow(followDTO);
                follow = followRepository.save(follow);
                return entityMapper.followToFollowDTO(follow);
        }

        public FollowDTO getFollowById(Long id) {
                Follow follow = followRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Follow not found"));
                return entityMapper.followToFollowDTO(follow);
        }
}
