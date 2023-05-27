package com.example.board;

import com.example.board.entitiy.BoardEntity;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

//    private final BoardRepository boardRepository;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void initData() {
//        log.info("test data init");
//        boardRepository.save(BoardEntity.builder()
//                .boardWriter("가가가가")
//                .boardPass("1234")
//                .boardTitle("제목1")
//                .boardContents("안녕하세요~~")
//                .boardHits(0)
//                .build());
//        boardRepository.save(BoardEntity.builder()
//                .boardWriter("나나나나")
//                .boardPass("4312")
//                .boardTitle("제목2")
//                .boardContents("반가워요~~~")
//                .boardHits(0)
//                .build());
//    }
}
