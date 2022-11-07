package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
//    private final HttpSession httpSession; -> @LoginUser 애노테이션으로 반복되는 코드를 대체하므로 필요없음

    @GetMapping("/")
    public String index(Model model , @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        //SessionUser user = (SessionUser) httpSession.getAttribute("user"); // CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장했음 -> httpSession.setAttribute("user", new SessionUser(user)); / -> @LoginUser 애노테이션으로 반복되는 코드를 대체하므로 필요없음

        if(user != null){
            model.addAttribute("userName" , user.getName()); // 세션에 저장된 값이 있을 때만 model에 userName으로 등록
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

}
