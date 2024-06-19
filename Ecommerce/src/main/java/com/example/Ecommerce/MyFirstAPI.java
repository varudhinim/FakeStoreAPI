package com.example.Ecommerce;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyFirstAPI {


    @RequestMapping("/hello")
    // http://url-of-myServer/hello
    public String SayHello(){
        return "Hello World";
    }
    //http://url-of-myServer/hello/ {name}
    @RequestMapping("/{name}")
    public String SayHelloToName(@PathVariable("name") String name){
    return "Hello " + name;
    }
}
