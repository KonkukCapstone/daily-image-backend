package com.project.diaryai.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PromptReqDto {

    private String loraParameter; // lora 모델 파라미터
    private String summarizedContent; // 요약된 일기 내용

}
