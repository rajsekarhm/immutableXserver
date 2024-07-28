package com.immutable.request;
import com.immutable.request.accounts.UserDAO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RequestDemo {
    @RequestMapping("/hello")
    public  String printHello(@RequestBody UserDAO user){
        return  "Hello Bro From Server";
    }

    @RequestMapping("/")
    public Object handlingHome(){
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        return names;
    }
}
