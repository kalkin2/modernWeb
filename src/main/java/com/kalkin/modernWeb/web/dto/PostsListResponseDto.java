package com.kalkin.modernWeb.web.dto;

import com.kalkin.modernWeb.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 글 조회시 dto
 */
@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;


    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
