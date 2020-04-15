package com.kalkin.modernWeb.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     *  프로파일이 있으면  없으면 첫번째가(real1) 조회된다 .
     */
    @Test
    public void get_real_profile(){
        //given
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        //when
        String profiles = controller.profile();
        System.out.println("profiles=="+profiles);
        System.out.println("expectedProfile=="+expectedProfile);

        //then
        assertThat(profiles).isEqualTo(expectedProfile);
    }


    /**
     * active된 프로파일이 없으면 default 리턴한다.
     */
    @Test
    public void notExist_active_profile_return_default(){

        //given
        String expectedProfiel ="default";
        MockEnvironment env = new MockEnvironment();
        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        assertThat(profile).isEqualTo(expectedProfiel);

    }


    /**
     * 스프링 시큐리티 인증 통과 테스트
     * @throws Exception
     */
    @Test
    public void profile_is_pass_auth()throws Exception{
        String expected = "default";
        ResponseEntity<String> response = restTemplate.getForEntity("/profile",String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expected);
    }
}