package com.gom.space_beauty.dto;

import lombok.Builder;

@Builder
public record SpaceResponse(
        String date,
        String explanation,
        String url,
        String type,
        String title) {

}
