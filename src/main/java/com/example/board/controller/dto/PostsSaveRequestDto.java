package com.example.board.controller.dto;

import com.example.board.domain.posts.Posts;
import com.sun.istack.NotNull;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    @NotEmpty
    private String title;

    private String content;

    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
