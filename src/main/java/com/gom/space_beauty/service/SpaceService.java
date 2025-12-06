package com.gom.space_beauty.service;

import org.springframework.stereotype.Service;

import com.gom.space_beauty.client.SpaceClient;
import com.gom.space_beauty.dto.NasaApiResponse;
import com.gom.space_beauty.dto.SpaceResponse;

@Service
public class SpaceService {

    private SpaceClient spaceClient;

    public SpaceService(SpaceClient spaceClient) {
        this.spaceClient = spaceClient;
    }

    public SpaceResponse getPicture(final String date) {
        final NasaApiResponse response = spaceClient.getPicture(date);

        return SpaceResponse.builder()
                .date(response.date())
                .explanation(response.explanation())
                .url(response.url())
                .type(response.type())
                .title(response.title())
                .copyright(response.copyright())
                .build();
    }
}
