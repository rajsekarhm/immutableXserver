package com.immutable.services;

import com.immutable.request.accounts.UserIMX;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@RestController
public class Service {

    @RequestMapping("/create")
    public UserIMX createS(
            @RequestParam String userName,
            @RequestParam String email,
            @RequestParam Boolean isAgent,
            @RequestParam Long phoneNumber,
            @RequestParam String edition,
            @RequestParam Boolean isAuthForBuyAndSell,
            @RequestParam Long governmentID
    ) {
        UserIMX obj = new UserIMX();
        obj.setEmail(email);
        obj.setEdition(edition);
        obj.setGovernmentID(governmentID);
        obj.setIsAgent(isAgent);
        obj.setPhoneNumber(phoneNumber);
        obj.setUserName(userName);
        obj.setIsAuthForBuyAndSell(isAuthForBuyAndSell);
//        HttpResponse response = new HttpResponse() {
//            @Override
//            public int statusCode() {
//                return 0;
//            }
//
//            @Override
//            public HttpRequest request() {
//                return null;
//            }
//
//            @Override
//            public Optional<HttpResponse> previousResponse() {
//                return Optional.empty();
//            }
//
//            @Override
//            public HttpHeaders headers() {
//                return null;
//            }
//
//            @Override
//            public Object body() {
//                return null;
//            }
//
//            @Override
//            public Optional<SSLSession> sslSession() {
//                return Optional.empty();
//            }
//
//            @Override
//            public URI uri() {
//                return null;
//            }
//
//            @Override
//            public HttpClient.Version version() {
//                return null;
//            }
//        };
        return obj;
    }
}
