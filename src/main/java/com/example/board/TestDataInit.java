package com.example.board;


import com.example.board.controller.dto.PostsResponseDto;
import com.example.board.domain.posts.Posts;
import com.example.board.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final PostsRepository postsRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("test data init");
        postsRepository.save(Posts.builder()
                .title("글1")
                .content("가나다라마바사")
                .author("김부각")
                .build());

        postsRepository.save(Posts.builder()
                .title("글2")
                .content("zxcbzxcv")
                .author("맛김치")
                .build());

        postsRepository.save(Posts.builder()
                .title("글3")
                .content("qwetyhgbz")
                .author("쫄면")
                .build());

        postsRepository.save(Posts.builder()
                .title("글4")
                .content("asdfasdfa")
                .author("춤추는네오")
                .build());

        postsRepository.save(Posts.builder()
                .title("글5")
                .content("fasdfas")
                .author("배개에 파묻힌 프로도")
                .build());
    }
}
