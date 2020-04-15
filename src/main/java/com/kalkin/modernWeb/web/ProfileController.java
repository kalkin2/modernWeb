package com.kalkin.modernWeb.web;


import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 무중단 서비스를 위한 컨트롤러
 */

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment env;

    @GetMapping("/profile")
    public String profile(){

        /**
         * 현재 실행중인 ActiveProfile들을 가져와서(real,oauth,real-db)담는다.
         */
        List<String> profiles  = Arrays.asList(env.getActiveProfiles());
        System.out.println(">>>"+profiles);

        List<String>realProfiles = Arrays.asList("real","real1","real2");

        String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }


}
