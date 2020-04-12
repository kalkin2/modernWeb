package com.kalkin.mordenWeb.web;


import com.kalkin.mordenWeb.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto HelloDto(@RequestParam("name")String name ,@RequestParam("amount")int amount){
        return new HelloResponseDto(name,amount);
    }
}
