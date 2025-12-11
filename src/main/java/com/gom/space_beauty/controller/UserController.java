package com.gom.space_beauty.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gom.space_beauty.dto.SpaceResponse;
import com.gom.space_beauty.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/me/favorites")
    public ResponseEntity<SpaceResponse> addFavorite(@RequestBody SpaceResponse data) {
        return ResponseEntity.ok(userService.addFavorite(data));
    }

    @GetMapping("/me/favorites")
    public ResponseEntity<List<SpaceResponse>> getFavorites() {
        return ResponseEntity.ok(userService.getFavorites());
    }
}
