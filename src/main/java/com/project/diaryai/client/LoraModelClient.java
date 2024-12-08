package com.project.diaryai.client;

import com.project.diaryai.dto.ImageReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class LoraModelClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${lora.base-url}")
    private String baseUrl;

    // 이미지 생성 모델 api 호출
    public InputStreamResource generateImageWithLora(ImageReqDto imageReqDto) {
        WebClient webClient = webClientBuilder
                .baseUrl(baseUrl)
                .build();

        Resource resource = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("flux_lora") // 엔드포인트
                        .queryParam("lora_name", imageReqDto.getLora_name()) // lora_name 파라미터
                        .queryParam("generate_prompt", imageReqDto.getGenerate_prompt()) // generate_prompt 파라미터
                        .build())
                .accept(MediaType.IMAGE_PNG) // 이미지 파일 반환 예상
                .retrieve()
                .bodyToMono(Resource.class) // 이미지를 byte 배열로 변환
                .block();
        return new InputStreamResource(Objects.requireNonNull(resource));
    }
}
