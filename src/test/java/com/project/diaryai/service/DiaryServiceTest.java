package com.project.diaryai.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DiaryServiceTest {
    @Autowired
    private DiaryService diaryService;

    @Test
    void 이미지_생성_테스트() {
        String diaryContent = "오늘은 산책을 다녀왔다.";
        String imageUrl = diaryService.generateImage(diaryContent);
        assertThat(imageUrl).isNotNull();
    }
}
