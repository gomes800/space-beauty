package com.gom.space_beauty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NasaApiResponse(
                @JsonProperty("date") String date,
                @JsonProperty("explanation") String explanation,
                @JsonProperty("hdurl") String url,
                @JsonProperty("media_type") String type,
                @JsonProperty("title") String title) {

}
