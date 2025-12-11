package com.gom.space_beauty.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gom.space_beauty.dto.SpaceResponse;
import com.gom.space_beauty.entity.SpaceMedia;
import com.gom.space_beauty.entity.User;
import com.gom.space_beauty.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthService authService;
    private final SpaceMediaService spaceMediaService;

    public UserService(
            UserRepository userRepository,
            AuthService authService,
            SpaceMediaService spaceMediaService) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.spaceMediaService = spaceMediaService;
    }

    @Transactional
    public List<SpaceResponse> getFavorites() {
        User user = userRepository.findById(authService.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        return user.getFavorites()
                .stream()
                .map(favorite -> SpaceResponse.fromEntity(favorite))
                .toList();
    }

    @Transactional
    public SpaceResponse addFavorite(SpaceResponse data) {
        SpaceMedia media = spaceMediaService.saveMedia(data);

        User user = userRepository.findById(authService.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        boolean alreadyFavorite = user.getFavorites().stream()
                .anyMatch(fav -> fav.getId().equals(media.getId()));

        if (!alreadyFavorite) {
            user.getFavorites().add(media);
            userRepository.save(user);
        }

        return SpaceResponse.fromEntity(media);
    }

}
