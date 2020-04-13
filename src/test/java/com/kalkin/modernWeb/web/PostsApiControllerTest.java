package com.kalkin.modernWeb.web;


import com.kalkin.modernWeb.domain.posts.PostRepository;
import com.kalkin.modernWeb.domain.posts.Posts;
import com.kalkin.modernWeb.web.dto.PostUpdateRequestDto;
import com.kalkin.modernWeb.web.dto.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @After
    public void tearDown()throws Exception {
        postRepository.deleteAll();
    }

    @Test
    public void savePost()throws Exception{
        //given
        String title ="글등록 테스트";
        String content = "테스트 중입니다...잘되나요??";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("kalkin")
                .build();
        String saveUrl ="http://localhost:" +port +"/api/v1/posts";


        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(saveUrl,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> post  =postRepository.findAll();
        assertThat(post.get(0).getTitle()).isEqualTo(title);
        assertThat(post.get(0).getContent()).isEqualTo(content);

     }

    /**
     * 조회 테스
     */
    @Test
    public void getPostTest()throws Exception{

        //given
        //1. 글등록을 한다.

        Posts savePost = postRepository.save(Posts.builder()
                                                .author("kalkin3")
                                                .content("컨텐트1 ")
                                                .title("타이틀1")
                                                .build()
                                                );
        Long updateId = savePost.getId();
        String expectedTitle = "타이틀 수정 ";
        String expectedContent = "컨텐스 수정 ";

        PostUpdateRequestDto requestDto = PostUpdateRequestDto
                                            .builder()
                                            .title(expectedTitle)
                                            .content(expectedContent)
                                            .build();

        //url
        String url ="http://localhost:"+port+"/api/v1/posts/"+updateId;

        HttpEntity<PostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);


        //when
        ResponseEntity<Long>responseEntity = restTemplate.exchange(
                                            url
                                            , HttpMethod.PUT,
                                            requestEntity,Long.class);


        //then
        //1. 상태확인
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //2. 바디확인
        List<Posts> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

        //3. 조회하여 내용 확인



        //when
        //url을 호출하여 entity에 담는다

        //then
        //예상되는 값을 리턴받은 entity와 비교해 본다.

    }

}