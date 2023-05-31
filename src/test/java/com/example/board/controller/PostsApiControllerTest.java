package com.example.board.controller;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.board.controller.dto.PostsSaveRequestDto;
import com.example.board.controller.dto.PostsUpdateRequestDto;
import com.example.board.domain.posts.Posts;
import com.example.board.domain.posts.PostsRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PostsApiControllerTest {

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void init() {
        postsRepository.deleteAll();
    }

    @Test
    void save() {
        String title = "title";
        String content = "asdfasdfsadf";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("서현준")
                .build();

        Posts savePost = postsRepository.save(requestDto.toEntity());
        Posts findPost = postsRepository.findById(savePost.getId()).get();

        assertThat(savePost).isEqualTo(findPost);
    }

    @Test
    void update() {
        Posts posts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("서현준")
                .build());

        String updateTitle = "title2";
        String updateContent = "content2";

        Posts savedPost = postsRepository.save(posts);

        savedPost.update(updateTitle, updateContent);
        assertThat(savedPost.getTitle()).isEqualTo(updateTitle);
        assertThat(savedPost.getContent()).isEqualTo(updateContent);
    }
}