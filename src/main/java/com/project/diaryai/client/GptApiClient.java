package com.project.diaryai.client;

import java.util.Collections;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GptApiClient {
    private final WebClient webClient;

    public GptApiClient(WebClient.Builder wenClientBuilder) {
        this.webClient = wenClientBuilder.baseUrl("http://localhost:8000/api").build();
    }

    public String fetchPrompt(String diaryContent) {
        return webClient.post()
                .uri("/flux-prompt/")
                .bodyValue(Collections.singletonMap("content", diaryContent))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
