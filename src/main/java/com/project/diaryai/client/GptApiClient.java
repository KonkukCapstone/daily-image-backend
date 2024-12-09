package com.project.diaryai.client;

import com.project.diaryai.dto.PromptReqDto;
import com.project.diaryai.dto.SummationReqDto;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GptApiClient {
    private final WebClient webClient;

    @Value("${fast.base-url}")
    private String baseUrl;

    public GptApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    // 일기 요약본 기반으로 프롬프트 생성
    public String generatePrompt(String summary) {
        PromptReqDto request = PromptReqDto.builder()
                .summary(summary)
                .build();
        return webClient.post()
                .uri("/gpt-prompt")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // 키워드, 일기 요약본 생성
    public String generateDiaryHighlight(String diaryContent) {
        SummationReqDto request = SummationReqDto.builder()
                .diary(diaryContent)
                .build();

        return webClient.post()
                .uri("/gpt-kr-2")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
