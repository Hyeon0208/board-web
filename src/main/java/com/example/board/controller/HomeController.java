package com.example.board.controller;

import com.example.board.config.oauth.Login;
import com.example.board.config.oauth.SessionUser;
import com.example.board.controller.dto.ItemSaveRequestDto;
import com.example.board.controller.dto.PostsResponseDto;
import com.example.board.service.PostsService;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @Value("${file.dir}")
    private String fileDir;

    private final PostsService postsService;

    @GetMapping("/")
    public String home(@Login SessionUser user, Model model) {

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

    // @PageableDefault(page = 1) : page는 기본으로 1페이지를 보여준다.
    @GetMapping("/posts/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, @Login SessionUser user, Model model) {
        Page<PostsResponseDto> postsPages = postsService.paging(pageable);

        /**
         * page 개수 설정
         * 현재 사용자가 선택한 페이지 앞 뒤로 3페이지씩만 보여준다.
         * ex : 현재 사용자가 4페이지라면 2, 3, (4), 5, 6
         */
        int blockLimit = 3;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), postsPages.getTotalPages());
//        int endPage = ((startPage + blockLimit - 1) < postsPages.getTotalPages()) ? startPage + blockLimit - 1 : postsPages.getTotalPages();

        model.addAttribute("postsPages", postsPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("userName", user.getName());
        return "paging";
    }


}
