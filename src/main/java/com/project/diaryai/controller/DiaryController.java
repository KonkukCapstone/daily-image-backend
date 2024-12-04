package com.project.diaryai.controller;

import com.project.diaryai.dto.DiaryRequestDto;
import com.project.diaryai.dto.ImageResponseDto;
import com.project.diaryai.service.DiaryService;
import com.project.diaryai.service.PromptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    private final PromptService promptService;
    private final DiaryService diaryService;

    public DiaryController(PromptService promptService, DiaryService diaryService) {
        this.promptService = promptService;
        this.diaryService = diaryService;
    }

    @PostMapping("/generate-prompt")
    public String generatePrompt(@RequestBody String diaryText) {
        return promptService.generatePrompt(diaryText);
    }

    @PostMapping("/generate-image")
    public ResponseEntity<?> generateImage(@RequestBody DiaryRequestDto diaryRequestDto) {
        String imageUrl = diaryService.generateImage(diaryRequestDto.getDiaryContent());
        return ResponseEntity.ok(new ImageResponseDto(imageUrl));
    }
}
