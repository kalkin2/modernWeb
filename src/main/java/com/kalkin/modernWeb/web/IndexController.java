package com.kalkin.modernWeb.web;

import com.kalkin.modernWeb.config.auth.LoginUser;
import com.kalkin.modernWeb.config.auth.dto.SessionUser;
import com.kalkin.modernWeb.service.PostsService;
import com.kalkin.modernWeb.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    /**
     * 메인 페이지 글 전체 리스트
     */
    @GetMapping("/")
    public String index(Model model , @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAllDesc());

       // SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user!=null){
            model.addAttribute("userName",user.getName());
        }
        return "index"; // /resource/templates/index.mustache
    }

    /**
     * 등록 페이지 이동
     * @return
     */
    @GetMapping("/posts/save")
    public String postsSave(@LoginUser SessionUser user,Model model){
        if(user!=null){
            model.addAttribute("userName",user.getName());
        }
        return "posts-save";
    }

    /**
     * 수정 페이지 이동
     * @param : 글번호 id
     * @param  : model
     * @return
     */
    @GetMapping("/posts/update/{id}")
    public String update(@PathVariable Long id, Model model){
        PostResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
       return  "posts-update";
    }
}
