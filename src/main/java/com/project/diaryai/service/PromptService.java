package com.project.diaryai.service;

import com.project.diaryai.client.GptApiClient;
import com.project.diaryai.dto.PromptReqDto;
import com.project.diaryai.dto.PromptResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromptService {
    private final GptApiClient gptApiClient;

    // 프롬프트 생성
    public PromptResDto generatePrompt(PromptReqDto promptReqDto) {

        // lora 파라미터, 요약된 일기 문장 추출
        String loraParameter = promptReqDto.getLoraParameter();
        String summarizedContent = promptReqDto.getSummarizedContent();

        // gpt api 호출 후 프롬프트 생성
        String rawPrompt = gptApiClient.generatePrompt(summarizedContent);
        String processedPrompt = processPrompt(loraParameter, rawPrompt);

        return PromptResDto.builder()
                .processedPrompt(processedPrompt)
                .build();
    }

    // 프롬프트 엔지니어링 : 공백 제거, 쉼표 추가, TriggerWord 추가
    private String processPrompt(String loraParameter, String rawPrompt) {
        String triggerWord = getTriggerWord(loraParameter);
        return String.format("%s %s", triggerWord.trim(), rawPrompt.trim());
    }

    // lora 모델 파라미터에 따른 TriggerWord 반환
    public String getTriggerWord(String loraParameter) {
        if ("rapunzel.safetensors".equals(loraParameter)) {
            return "RAPUNZEL, beautiful teenager rapunzel, girl, ";
        } else if ("3d.safetensors".equals(loraParameter)) {
            return "";
        }
        return "";
    }
}