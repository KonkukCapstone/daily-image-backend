package com.project.diaryai.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SummationReqDto {
    
    private String diary_content; // 일기 내용
}
