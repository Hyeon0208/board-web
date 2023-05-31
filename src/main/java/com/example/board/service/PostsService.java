package com.example.board.service;

import com.example.board.controller.dto.PostsListResponseDto;
import com.example.board.controller.dto.PostsResponseDto;
import com.example.board.controller.dto.PostsSaveRequestDto;
import com.example.board.controller.dto.PostsUpdateRequestDto;
import com.example.board.domain.posts.Posts;
import com.example.board.domain.posts.PostsRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Posts save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity());
    }

    @Transactional
    public Posts update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return posts;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new PostsResponseDto(posts);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow();
        postsRepository.delete(posts);
    }
}
