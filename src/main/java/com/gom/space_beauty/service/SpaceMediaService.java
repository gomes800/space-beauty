package com.gom.space_beauty.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gom.space_beauty.dto.SpaceResponse;
import com.gom.space_beauty.entity.SpaceMedia;
import com.gom.space_beauty.repository.SpaceMediaRepository;

@Service
public class SpaceMediaService {

    private final SpaceMediaRepository spaceMediaRepository;

    public SpaceMediaService(SpaceMediaRepository spaceMediaRepository) {
        this.spaceMediaRepository = spaceMediaRepository;
    }

    public SpaceMedia saveMedia(SpaceResponse data) {
        Optional<SpaceMedia> existing = spaceMediaRepository.findByUrl(data.url());
        if (existing.isPresent()) {
            return existing.get();
        }

        SpaceMedia media = new SpaceMedia();
        media.setTitle(data.title());
        media.setDate(data.date());
        media.setExplanation(data.explanation());
        media.setUrl(data.url());
        media.setType(data.type());
        media.setCopyright(data.copyright());

        return spaceMediaRepository.save(media);
    }
}
