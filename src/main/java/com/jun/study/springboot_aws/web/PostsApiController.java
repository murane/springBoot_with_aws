package com.jun.study.springboot_aws.web;

import com.jun.study.springboot_aws.service.posts.PostsService;
import com.jun.study.springboot_aws.web.dto.PostsResponseDto;
import com.jun.study.springboot_aws.web.dto.PostsSaveRequestDto;
import com.jun.study.springboot_aws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto reqDto) {
        return postsService.save(reqDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}