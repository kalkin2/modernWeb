package com.kalkin.modernWeb.web.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @After
    public void cleanUp(){
        postRepository.deleteAll();
    }


    /**
     * 게시글 저장 후 불러오기
     */
    @Test
    public void getBoardAll(){
        //given
        String title ="test 게시글 1";
        String content = "테스트 본문";

        postRepository.save(Posts.builder()
                            .title(title)
                            .content(content)
                            .author("kalkin2@naver.com")
                            .build()
                            )
        ;


        //when
        List<Posts> postsList = postRepository.findAll();


        //then
        Posts post = postsList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

}