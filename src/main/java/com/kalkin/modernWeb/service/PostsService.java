package com.kalkin.modernWeb.service;

import com.kalkin.modernWeb.domain.posts.PostRepository;
import com.kalkin.modernWeb.domain.posts.Posts;
import com.kalkin.modernWeb.web.dto.PostResponseDto;
import com.kalkin.modernWeb.web.dto.PostUpdateRequestDto;
import com.kalkin.modernWeb.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
       return  postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(long id, PostUpdateRequestDto requestDto){
        Posts posts = postRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("해당 게시글이 업습니다. id="+id));
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    public PostResponseDto findById(Long id){
        Posts entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다"));
        return new PostResponseDto(entity);
    }
}
