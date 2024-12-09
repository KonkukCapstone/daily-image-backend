package com.project.diaryai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.diaryai.client.GptApiClient;
import com.project.diaryai.dto.KeywordsResDto;
import com.project.diaryai.dto.SummationReqDto;
import com.project.diaryai.dto.SummationResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SummationService {
    private final GptApiClient gptApiClient;

    public SummationResDto generateSummation(SummationReqDto summationReqDto) {
        String diaryContent = summationReqDto.getDiary();

        String result = gptApiClient.generateDiaryHighlight(diaryContent);

        String summary = extractDiarySummation(result);
        List<String> emotionKeywords = extractEmotionKeyword(result);
        List<String> activityKeywords = extractActivityKeyword(result);
        List<String> locationKeywords = extractLocationKeyword(result);

        KeywordsResDto keywords = KeywordsResDto.builder()
                .activity_keywords(activityKeywords)
                .emotion_keywords(emotionKeywords)
                .location_keywords(locationKeywords)
                .build();

        return SummationResDto.builder()
                .keywords(keywords)
                .summary(summary)
                .build();
    }

    private String extractDiarySummation(String result) {
        return parseJsonForString(result, "summary");
    }

    private List<String> extractEmotionKeyword(String result) {
        return parseJsonForList(result, "emotion_keywords");
    }

    private List<String> extractActivityKeyword(String result) {
        return parseJsonForList(result, "activity_keywords");
    }

    private List<String> extractLocationKeyword(String result) {
        return parseJsonForList(result, "location_keywords");
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

    private String parseJsonForString(String result, String key) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(result);
            JsonNode keyNode = jsonNode.get(key);
            if (keyNode != null) {
                return keyNode.asText(); // 단일 문자열 값 반환
            } else {
                System.err.println("Key not found in JSON: " + key);
            }
        } catch (JsonProcessingException e) {
            // JSON 처리 중 예외 발생
            e.printStackTrace();
        }
        return null; // 예외 발생 또는 키가 없는 경우 null 반환
    }

    private List<String> parseJsonForList(String result, String key) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(result);
            JsonNode keywordsNode = jsonNode.get("keywords"); // "keywords" 노드
            if (keywordsNode != null) {
                JsonNode keyNode = keywordsNode.get(key); // 특정 키의 노드
                if (keyNode != null && keyNode.isArray()) {
                    List<String> list = new ArrayList<>();
                    for (JsonNode node : keyNode) {
                        list.add(node.asText());
                    }
                    return list; // 배열 값을 리스트로 반환
                } else {
                    System.err.println("Key not found or not an array: " + key);
                }
            } else {
                System.err.println("\"keywords\" node not found in JSON.");
            }
        } catch (JsonProcessingException e) {
            // JSON 처리 중 예외 발생
            e.printStackTrace();
        }
        return Collections.emptyList(); // 예외 발생 또는 키가 없는 경우 빈 리스트 반환
    }


}
