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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

    public Page<PostsResponseDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 3; // 한페이지에 보여줄 글 개수

        // 한 페이지당 3개식 글을 보여주고 정렬 기준은 ID기준으로 내림차순
        Page<Posts> postsPages = postsRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Direction.DESC, "id")));

        // 페이지 정보
        System.out.println("postsPages.getContent() = " + postsPages.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("postsPages.getTotalElements() = " + postsPages.getTotalElements()); // 전체 글갯수
        System.out.println("postsPages.getNumber() = " + postsPages.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("postsPages.getTotalPages() = " + postsPages.getTotalPages()); // 전체 페이지 갯수
        System.out.println("postsPages.getSize() = " + postsPages.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("postsPages.hasPrevious() = " + postsPages.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("postsPages.isFirst() = " + postsPages.isFirst()); // 첫 페이지 여부
        System.out.println("postsPages.isLast() = " + postsPages.isLast()); // 마지막 페이지 여부

        // 목록 : id, title, content, author, createdTime
        Page<PostsResponseDto> postsResponseDtos = postsPages.map(
                postPage -> new PostsResponseDto(postPage));

        return postsResponseDtos;
    }
}
