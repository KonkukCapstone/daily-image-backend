package com.project.diaryai.service;

import com.project.diaryai.client.GptApiClient;
import com.project.diaryai.client.LoraModelClient;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
    private final GptApiClient gptApiClient;
    private final LoraModelClient loraModelClient;

    public DiaryService(GptApiClient gptApiClient, LoraModelClient loraModelClient) {
        this.gptApiClient = gptApiClient;
        this.loraModelClient = loraModelClient;
    }

    public String generateImage(String diaryContent) {
        String prompt = gptApiClient.generatePrompt(diaryContent);
        return loraModelClient.generateImageFromPrompt(prompt);
    }
}
