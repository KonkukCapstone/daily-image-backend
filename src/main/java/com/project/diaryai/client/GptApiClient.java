package com.project.diaryai.client;

import java.util.Collections;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GptApiClient {
    private final WebClient webClient;

    public GptApiClient(WebClient.Builder wenClientBuilder) {
        // FastAPI url 필요
        this.webClient = wenClientBuilder.baseUrl("FastAPI URL").build();
    }

    public String generatePrompt(String diaryContent) {
        return webClient.post()
                .uri("/api/generate-prompt")
                .bodyValue(Collections.singletonMap("content", diaryContent))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
