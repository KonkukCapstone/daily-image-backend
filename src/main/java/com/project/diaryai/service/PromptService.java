package com.project.diaryai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        String loraParameter = promptReqDto.getLora_name();
        String summarizedContent = promptReqDto.getSummary();

        // gpt api 호출 후 프롬프트 생성
        String rawPrompt = gptApiClient.generatePrompt(summarizedContent);
        String processedPrompt = processPrompt(loraParameter, rawPrompt);

        return PromptResDto.builder()
                .processed_prompt(processedPrompt)
                .build();
    }

    // 프롬프트 엔지니어링 : 공백 제거, 쉼표 추가, TriggerWord 추가
    private String processPrompt(String loraParameter, String rawPrompt) {
        String triggerWord = getTriggerWord(loraParameter);
        String positivePrompt = extractPositivePrompt(rawPrompt);
        return String.format("%s %s", triggerWord.trim(), positivePrompt.trim());
    }

    // JSON 데이터에서 "positive_prompt" 추출
    private String extractPositivePrompt(String rawPrompt) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(rawPrompt);
            return jsonNode.get("positive_prompt").asText(); // positive_prompt 값 반환
        } catch (JsonProcessingException e) {
            // 예외 처리: 로그를 출력하거나 기본 메시지 반환
            e.printStackTrace();
            return "Error parsing prompt.";
        }
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