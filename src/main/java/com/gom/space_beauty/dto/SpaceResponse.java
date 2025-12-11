package com.gom.space_beauty.dto;

import com.gom.space_beauty.entity.SpaceMedia;

import lombok.Builder;

@Builder
public record SpaceResponse(
                Long id,
                String date,
                String explanation,
                String url,
                String type,
                String title,
                String copyright) {

        public static SpaceResponse fromEntity(SpaceMedia spaceMedia) {
                return SpaceResponse.builder()
                                .id(spaceMedia.getId())
                                .title(spaceMedia.getTitle())
                                .explanation(spaceMedia.getExplanation())
                                .date(spaceMedia.getDate())
                                .type(spaceMedia.getType())
                                .url(spaceMedia.getUrl())
                                .copyright(spaceMedia.getCopyright())
                                .build();
        }

}
