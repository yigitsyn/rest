package com.exampleRest.Rest.Controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class HelloWorldController {

    // HTTP Get Request
    //http://localhost:8080/v1/hello-world
    @GetMapping("/v1/hello-world")
    public List<String> helloWorld() {
       List hello = new ArrayList();
        for(int i =0; i<20; i++){
            hello.add("Hello World " + (i + 1));
        }
        return hello;
    }



}
