package com.kalkin.modernWeb.service;

import com.kalkin.modernWeb.domain.posts.PostRepository;
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
}
