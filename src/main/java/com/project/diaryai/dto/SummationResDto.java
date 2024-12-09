package com.project.diaryai.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SummationResDto {

    private String diarySummation; // 요약된 일기 내용
    private String emotionKeyword; // 키워드
    private String activityKeyword;
    private String locationKeyword;
}
