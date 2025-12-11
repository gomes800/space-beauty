package com.gom.space_beauty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gom.space_beauty.entity.SpaceMedia;

public interface SpaceMediaRepository extends JpaRepository<SpaceMedia, Long> {
    Optional<SpaceMedia> findByUrl(String url);
}
