package com.project.diaryai.controller;

import com.project.diaryai.dto.DiaryRequestDto;
import com.project.diaryai.dto.ImageResponseDto;
import com.project.diaryai.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/diary")
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/generate-image")
    public ResponseEntity<?> generateImage(@RequestBody DiaryRequestDto diaryRequestDto) {
        String imageUrl = diaryService.generateImage(diaryRequestDto.getDiaryContent());
        return ResponseEntity.ok(new ImageResponseDto(imageUrl));
    }
}
