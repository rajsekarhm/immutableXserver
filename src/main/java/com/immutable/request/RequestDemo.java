package com.immutable.request;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RequestDemo {
    @RequestMapping("/hello")
    public  String printHello(){
        return  "Hello Bro From Server";
    }

    @RequestMapping("/")
    public Object handlingHome(){
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        return names;
    }
}
