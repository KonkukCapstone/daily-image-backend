package com.project.diaryai.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SummationResDto {

    private KeywordsResDto keywords;
    private String summary;
}
