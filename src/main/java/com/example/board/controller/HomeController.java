package com.example.board.controller;

import com.example.board.config.oauth.Login;
import com.example.board.config.oauth.SessionUser;
import com.example.board.controller.dto.PostsResponseDto;
import com.example.board.service.PostsService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostsService postsService;

    @GetMapping("/")
    public String home(@Login SessionUser user, Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        // 세션에 저장된 값이 있을 때만 model에 userName 등록
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
