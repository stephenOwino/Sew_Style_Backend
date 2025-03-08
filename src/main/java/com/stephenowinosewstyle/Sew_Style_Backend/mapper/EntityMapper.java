package com.stephenowinosewstyle.Sew_Style_Backend.mapper;

import com.stephenowinosewstyle.Sew_Style_Backend.dto.*;
import com.stephenowinosewstyle.Sew_Style_Backend.entity.*;
import org.springframework.stereotype.Component;


@Component
public class EntityMapper {

        public UserDTO userToUserDTO(User user) {
                if (user == null) return null;

                UserDTO dto = new UserDTO();
                dto.setId(user.getId());
                dto.setFirstName(user.getFirstName());
                dto.setLastName(user.getLastName());
                dto.setEmail(user.getEmail());
                dto.setRole(user.getRole());
                dto.setActive(user.isEnabled()); // Map isEnabled to isActive
                return dto;
        }

        public User userDTOToUser(UserDTO dto) {
                if (dto == null) return null;

                User user = new User();
                user.setId(dto.getId());
                user.setFirstName(dto.getFirstName());
                user.setLastName(dto.getLastName());
                user.setEmail(dto.getEmail());
                user.setRole(dto.getRole());
                user.setEnabled(dto.isActive()); // Map isActive to isEnabled
                return user;
        }

        public TailorDTO tailorToTailorDTO(Tailor tailor) {
                if (tailor == null) return null;

                TailorDTO dto = new TailorDTO();
                dto.setId(tailor.getId());
                dto.setUser(userToUserDTO(tailor.getUser()));
                dto.setBio(tailor.getBio());
                dto.setSpecialization(tailor.getSpecialization());
                dto.setExperienceYears(tailor.getExperienceYears());
                return dto;
        }

        public Tailor tailorDTOToTailor(TailorDTO dto) {
                if (dto == null) return null;

                Tailor tailor = new Tailor();
                tailor.setId(dto.getId());
                tailor.setUser(userDTOToUser(dto.getUser()));
                tailor.setBio(dto.getBio());
                tailor.setSpecialization(dto.getSpecialization());
                tailor.setExperienceYears(dto.getExperienceYears());
                return tailor;
        }



        // ===== IMAGE MAPPING =====
        public ImageDTO imageToImageDTO(Image image) {
                if (image == null) return null;
                ImageDTO dto = new ImageDTO();
                dto.setId(image.getId());
                dto.setImageUrl(image.getImageUrl());
                dto.setLikeCount(image.getLikeCount());
                dto.setUploadedAt(image.getUploadedAt()); // Ensure this is included
                if (image.getGallery() != null) {
                        dto.setGalleryId(image.getGallery().getId());
                }
                return dto;
        }

        public Image imageDTOToImage(ImageDTO dto) {
                if (dto == null) return null;
                Image image = new Image();
                image.setId(dto.getId());
                image.setImageUrl(dto.getImageUrl());
                image.setLikeCount(dto.getLikeCount());
                // Do NOT set uploadedAt here; let @CreationTimestamp handle it
                return image;
        }


        // ===== GALLERY MAPPING =====
        public GalleryDTO galleryToGalleryDTO(Gallery gallery) {
                if (gallery == null) return null;
                GalleryDTO dto = new GalleryDTO();
                dto.setId(gallery.getId());
                dto.setTailorId(gallery.getTailor() != null ? gallery.getTailor().getId() : null);
                dto.setTitle(gallery.getTitle());
                dto.setDescription(gallery.getDescription());
                dto.setUserId(gallery.getTailor().getUser().getId());
                dto.setCreatedAt(gallery.getCreatedAt());
                if (gallery.getImages() != null) {
                        dto.setImages(gallery.getImages().stream().map(this::imageToImageDTO).toList());
                }
                return dto;
        }

        public Gallery galleryDTOToGallery(GalleryDTO dto) {
                if (dto == null) return null;
                Gallery gallery = new Gallery();
                gallery.setId(dto.getId());
                gallery.setTitle(dto.getTitle());
                gallery.setDescription(dto.getDescription());
                if (dto.getImages() != null) {
                        gallery.setImages(dto.getImages().stream().map(this::imageDTOToImage).toList());
                }
                return gallery;
        }

        // ===== FOLLOW MAPPING =====
        public FollowDTO followToFollowDTO(Follow follow) {
                if (follow == null) return null;

                FollowDTO dto = new FollowDTO();
                dto.setId(follow.getId());
                if (follow.getFollower() != null) {
                        dto.setFollowerId(follow.getFollower().getId());  // handle relationship
                }
                if (follow.getTailor() != null) {
                        dto.setTailorId(follow.getTailor().getId());  // handle relationship
                }
                return dto;
        }

        public Follow followDTOToFollow(FollowDTO dto) {
                if (dto == null) return null;

                Follow follow = new Follow();
                follow.setId(dto.getId());
                // Follower and Tailor should be fetched from DB using followerId and tailorId
                return follow;
        }

        // ===== COMMENT MAPPING =====
        public CommentDTO commentToCommentDTO(Comment comment) {
                if (comment == null) return null;

                CommentDTO dto = new CommentDTO();
                dto.setId(comment.getId());
                dto.setText(comment.getText());
                if (comment.getUser() != null) {
                        dto.setUserId(comment.getUser().getId());  // handle relationship
                }
                if (comment.getImage() != null) {
                        dto.setImageId(comment.getImage().getId());  // handle relationship
                }
                return dto;
        }

        public Comment commentDTOToComment(CommentDTO dto) {
                if (dto == null) return null;

                Comment comment = new Comment();
                comment.setId(dto.getId());
                comment.setText(dto.getText());
                // User and Image should be fetched from DB using userId and imageId
                return comment;
        }
}

