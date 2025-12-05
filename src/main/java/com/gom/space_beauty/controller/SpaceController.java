package com.gom.space_beauty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gom.space_beauty.dto.SpaceResponse;
import com.gom.space_beauty.service.SpaceService;

@RequestMapping("/api/space")
@RestController
public class SpaceController {

    private final SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @GetMapping("/{date}")
    public ResponseEntity<SpaceResponse> getPicture(@PathVariable String date) {
        return ResponseEntity.ok(spaceService.getPicture(date));
    }
}
