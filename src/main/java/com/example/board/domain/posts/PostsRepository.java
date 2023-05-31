package com.example.board.domain.posts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // QueryDSL
    List<Posts> findAllDesc(); // Spring DATA Jpa에서 제공하는 메서드
}
