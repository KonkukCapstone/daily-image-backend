package com.project.diaryai.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SummationResDto {

    private String diarySummation; // 요약된 일기 내용
    private String emotionKeyword;
    private String activityKeyword;
    private String locationKeyword;
}
