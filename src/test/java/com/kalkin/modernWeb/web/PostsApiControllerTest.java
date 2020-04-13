package com.kalkin.modernWeb.web;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.kalkin.modernWeb.domain.posts.PostRepository;
import com.kalkin.modernWeb.domain.posts.Posts;
import com.kalkin.modernWeb.web.dto.PostUpdateRequestDto;
import com.kalkin.modernWeb.web.dto.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        //loginSession
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown()throws Exception {
        postRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void savePost()throws Exception{
        //given
        String title ="aaaaa";
        String content = "bbbbb";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("kalkin")
                .build();


        String saveUrl ="http://localhost:" +port +"/api/v1/posts";


        //when
        //login logic before
       // ResponseEntity<Long> responseEntity = restTemplate.postForEntity(saveUrl,requestDto,Long.class);
        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //login login add after..
        mvc.perform(post(saveUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        List<Posts> post  =postRepository.findAll();
        assertThat(post.get(0).getTitle()).isEqualTo(title);
        assertThat(post.get(0).getContent()).isEqualTo(content);

     }

    /**
     * 수정  테스트
     */
    @Test
    @WithMockUser(roles="USER")
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
//        ResponseEntity<Long>responseEntity = restTemplate.exchange(
//                                            url
//                                            , HttpMethod.PUT,
//                                            requestEntity,Long.class);
//
//
//        //then
//        //1. 상태확인
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Posts> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);



    }

}