package com.project.diaryai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.diaryai.client.GptApiClient;
import com.project.diaryai.dto.SummationReqDto;
import com.project.diaryai.dto.SummationResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummationService {
    private final GptApiClient gptApiClient;

    public SummationResDto generateSummation(SummationReqDto summationReqDto) {
        String diaryContent = summationReqDto.getDiary_content();

        String result = gptApiClient.generateDiaryHighlight(diaryContent);
        
        String diarySummation = extractDiarySummation(result);
        String emotionKeyword = extractEmotionKeyword(result);
        String activityKeyword = extractActivityKeyword(result);
        String locationKeyword = extractLocationKeyword(result);

        return SummationResDto.builder()
                .diarySummation(diarySummation)
                .emotionKeyword(emotionKeyword)
                .activityKeyword(activityKeyword)
                .locationKeyword(locationKeyword)
                .build();
    }

    private String extractDiarySummation(String result) {
        return parseJson(result, "diarySummation");
    }

    private String extractEmotionKeyword(String result) {
        return parseJson(result, "emotionKeyword");
    }

    private String extractActivityKeyword(String result) {
        return parseJson(result, "activityKeyword");
    }

    private String extractLocationKeyword(String result) {
        return parseJson(result, "locationKeyword");
    }

    private String parseJson(String result, String key) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(result);
            return jsonNode.get(key).asText(); // 원하는 키의 값을 반환
        } catch (JsonProcessingException e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }

}
