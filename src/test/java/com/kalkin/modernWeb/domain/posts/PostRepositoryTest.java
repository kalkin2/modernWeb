package com.kalkin.modernWeb.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Test
    public void BaseTimeEntityTest(){

        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postRepository.save(Posts.builder().title("title").content("시간테스트").author("kalkin").build());

        //when
        List<Posts>postsList = postRepository.findAll();

        //then
        Posts post = postsList.get(0);

        System.out.println(">>>>>>>>>>createDate = "+post.getCreateDate());
        System.out.println(">>>>>>>>>>ModifiedDate = "+post.getModifiedDate());
        assertThat(post.getCreateDate().isAfter(now));
        assertThat(post.getModifiedDate().isAfter(now));
    }

}