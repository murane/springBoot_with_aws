package com.jun.study.springboot_aws.web;

import com.jun.study.springboot_aws.domain.posts.Posts;
import com.jun.study.springboot_aws.domain.posts.PostsRepository;
import com.jun.study.springboot_aws.domain.user.Role;
import com.jun.study.springboot_aws.web.dto.PostsResponseDto;
import com.jun.study.springboot_aws.web.dto.PostsSaveRequestDto;
import com.jun.study.springboot_aws.web.dto.PostsUpdateRequestDto;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private PostsRepository postsRepository;
    @AfterEach
    void 정리() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    void Posts_등록() throws Exception{
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();
        Long resId = webTestClient.post()
                .uri("/api/v1/posts")
                .body(Mono.just(requestDto), PostsSaveRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Long.class)
                .returnResult()
                .getResponseBody();
        assertThat(resId).isPositive();

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser(roles = "USER")
    void Posts_수정() throws Exception{
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto =
                PostsUpdateRequestDto.builder()
                        .title(expectedTitle)
                        .content(expectedContent)
                        .build();

        Long resId = webTestClient.put()
                .uri("/api/v1/posts/"+updateId)
                .body(Mono.just(requestDto),PostsUpdateRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Long.class)
                .returnResult()
                .getResponseBody();
        assertThat(resId).isPositive();

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
    }
}
