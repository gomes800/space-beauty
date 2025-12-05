package com.gom.space_beauty.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gom.space_beauty.dto.NasaApiResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpaceClient {

    private final WebClient webClient;

    @Value("${nasaApi.api-key}")
    private String apiKey;

    public NasaApiResponse getPicture(String date) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("api_key", apiKey)
                        .queryParam("date", date)
                        .build())
                .retrieve()
                .bodyToMono(NasaApiResponse.class)
                .block();
    }

}
