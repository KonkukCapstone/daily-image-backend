package com.project.diaryai.client;

import java.util.Collections;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class LoraModelClient {
    private final WebClient webClient;

    public LoraModelClient(WebClient.Builder wenClientBuilder) {
        this.webClient = wenClientBuilder.baseUrl("http://fastapi-server-url").build();
    }

    public String generateImageFromPrompt(String prompt) {
        return webClient.post()
                .uri("/api/generate-image")
                .bodyValue(Collections.singletonMap("prompt", prompt))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
