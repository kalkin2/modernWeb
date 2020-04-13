package com.kalkin.modernWeb.service;

import com.kalkin.modernWeb.domain.posts.PostRepository;
import com.kalkin.modernWeb.domain.posts.Posts;
import com.kalkin.modernWeb.web.dto.PostResponseDto;
import com.kalkin.modernWeb.web.dto.PostUpdateRequestDto;
import com.kalkin.modernWeb.web.dto.PostsListResponseDto;
import com.kalkin.modernWeb.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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


    /**
     * 글번호 를 이용하여 글 조회
     * @param id
     * @return PostResponseDto
     */
    public PostResponseDto findById(Long id){
        Posts entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다"));
        return new PostResponseDto(entity);
    }

    /**
     * 전체 글 조회
     * @return  List<PostsListResponseDto>
     */
    @Transactional(readOnly = true)//트랜젝션 범위는 유지하되 조회기능만 남겨두어 조회 속도 개선
    public List<PostsListResponseDto> findAllDesc(){
        //lamda
        return postRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());

    }


    /**
     * 글 삭제
     * @param id
     */
    @Transactional
    public void delete(Long id){
        Posts post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        postRepository.delete(post);

    }
}
