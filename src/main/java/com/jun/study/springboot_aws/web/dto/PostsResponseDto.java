package com.jun.study.springboot_aws.web.dto;

import com.jun.study.springboot_aws.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
        this.title = entity.getTitle();
    }
}
