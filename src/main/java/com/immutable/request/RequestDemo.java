package com.immutable.request;
import com.immutable.request.accounts.user.User;
import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Getter
@Setter
@NoArgsConstructor
public class RequestDemo {

    @RequestMapping("/hello")
    public  String printHello(@RequestBody User user){
        return  "Hello Bro From Server";
    }

    @RequestMapping("/")
    public Object handlingHome(){
         return  "Hello Bro From Server";
    }
}
