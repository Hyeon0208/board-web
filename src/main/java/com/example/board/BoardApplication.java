package com.example.board;

import com.example.board.domain.posts.PostsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing // 시간 측정 기능 활성화
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

	@Bean
	public TestDataInit testDataInit(PostsRepository postsRepository) {
		return new TestDataInit(postsRepository);
	}
}
