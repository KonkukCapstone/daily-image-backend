package com.project.diaryai.service;

import com.project.diaryai.client.GptApiClient;
import org.springframework.stereotype.Service;

@Service
public class PromptService {
    private final GptApiClient gptApiClient;

    public PromptService(GptApiClient gptApiClient) {
        this.gptApiClient = gptApiClient;
    }

    public String generatePrompt(String diaryText) {
        String rawPrompt = gptApiClient.fetchPrompt(diaryText);
        return processPrompt(rawPrompt);
    }

    // 프롬프트 엔지니어링
    private String processPrompt(String rawPrompt) {
        return rawPrompt.trim();
    }
}
