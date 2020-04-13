package com.kalkin.modernWeb.web;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class indexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    /**
     * /index 를 호출했을 경우 view(index.mustache) 페이지를 정확히 호출 하는지 확인
     */
    @Test
    public void indexTest(){
        //when
        String body = restTemplate.getForObject("/",String.class);
        //then
        Assertions.assertThat(body).contains("hello world!");
    }

}