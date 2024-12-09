package com.project.diaryai.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SummationReqDto {
    
    private String diary; // 일기 내용
}
