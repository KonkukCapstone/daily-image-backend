package com.project.diaryai.controller;

import com.project.diaryai.client.LoraModelClient;
import com.project.diaryai.dto.ImageReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ImageController {

    private final LoraModelClient loraModelClient;

    @PostMapping("/generate-image")
    public ResponseEntity<InputStreamResource> generateImage(@RequestBody ImageReqDto imageReqDto) {
        InputStreamResource imageStream = loraModelClient.generateImageWithLora(imageReqDto);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG) // 반환 이미지 형식 지정
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"generated_image.png\"") // 파일명 설정
                .body(imageStream);
    }
}
