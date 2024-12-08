package com.project.diaryai.client;

import java.util.Collections;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GptApiClient {
    private final WebClient webClient;

    // TODO : fastAPI 서버 주소로 변경하기
    public GptApiClient(WebClient.Builder wenClientBuilder) {
        this.webClient = wenClientBuilder.baseUrl("http://localhost:8000/api").build();
    }

    // 일기 요약본 기반으로 프롬프트 생성
    public String generatePrompt(String summarizedContent) {
        return webClient.post()
                .uri("/flux-prompt/")
                .bodyValue(Collections.singletonMap("content", summarizedContent))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // 키워드, 일기 요약본 생성
}
