package com.project.diaryai.controller;

import com.project.diaryai.dto.SummationReqDto;
import com.project.diaryai.dto.SummationResDto;
import com.project.diaryai.service.SummationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SummationController {

    private final SummationService summationService;

    // 일기 요약
    @PostMapping("/diary-highlight")
    public ResponseEntity<SummationResDto> generateSummation(@RequestBody SummationReqDto summationReqDto) {
        SummationResDto response = summationService.generateSummation(summationReqDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
