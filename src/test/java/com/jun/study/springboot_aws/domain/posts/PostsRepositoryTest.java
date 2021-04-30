package com.jun.study.springboot_aws.domain.posts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;
    @Test
    void 게시글저장_불러오기(){
        String title  = "테스트게시글";
        String content = "테스트본문";

        postsRepository.save(Posts.builder()
                .author("murane@naver.com")
                .title(title)
                .content(content)
                .build()
        );

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
    @Test
    void BaseTimeEntity_등록(){
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .author("murane@naver.com")
                .title("title")
                .content("content")
                .build()
        );

        var postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
