package com.project.diaryai.service;

import com.project.diaryai.client.LoraModelClient;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
    private final PromptService promptService;
    private final LoraModelClient loraModelClient;

    public DiaryService(PromptService promptService, LoraModelClient loraModelClient) {
        this.promptService = promptService;
        this.loraModelClient = loraModelClient;
    }

    public String generateImage(String diaryContent) {
        String prompt = promptService.generatePrompt(diaryContent);
        return loraModelClient.generateImageFromPrompt(prompt);
    }
}
