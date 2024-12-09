package com.project.diaryai.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PromptResDto {

    private String processed_prompt; // 가공된 프롬프트

}
