package com.kalkin.modernWeb.web;


import com.kalkin.modernWeb.service.PostsService;
import com.kalkin.modernWeb.web.dto.PostResponseDto;
import com.kalkin.modernWeb.web.dto.PostUpdateRequestDto;
import com.kalkin.modernWeb.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }


    //수정
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id , @RequestBody  PostUpdateRequestDto dto){
        return postsService.update(id, dto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }


}
