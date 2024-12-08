package com.project.diaryai.controller;

import com.project.diaryai.dto.PromptReqDto;
import com.project.diaryai.dto.PromptResDto;
import com.project.diaryai.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PromptController {

    private final PromptService promptService;

    // 프롬프트 생성
    @PostMapping("/generate-prompt")
    public ResponseEntity<PromptResDto> generatePrompt(@RequestBody PromptReqDto promptReqDto) {
        PromptResDto response = promptService.generatePrompt(promptReqDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
