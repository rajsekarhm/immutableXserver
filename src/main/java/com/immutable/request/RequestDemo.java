package com.immutable.request;
import com.immutable.request.accounts.UserDAO;
import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Getter
@Setter
@NoArgsConstructor
public class RequestDemo {
    @Override
    public String toString() {
        return "RequestDemo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    String name;
    String id;

    public  RequestDemo(String _name, String _id){
        this.name = _name;
        this.id = _id;
    }


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
