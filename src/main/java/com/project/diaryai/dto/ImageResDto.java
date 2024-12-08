package com.project.diaryai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.core.io.InputStreamResource;

@Getter
@Builder
@AllArgsConstructor
public class ImageResDto {

    private InputStreamResource imageData;

}
