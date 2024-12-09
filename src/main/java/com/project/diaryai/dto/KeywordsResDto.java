package com.project.diaryai.dto;

import lombok.Builder;
import lombok.Getter;

import javax.annotation.processing.Generated;
import java.util.List;

@Getter
@Builder
public class KeywordsResDto {

    private List<String> activity_keywords;
    private List<String> emotion_keywords;
    private List<String> location_keywords;
}
