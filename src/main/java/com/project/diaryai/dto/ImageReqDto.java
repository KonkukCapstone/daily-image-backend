package com.project.diaryai.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageReqDto {

    private String lora_name; // lora 모델 파라미터
    private String generate_prompt; // 프롬프트

}
