package com.immutable.authorization;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        String errorMessage = (String) request.getAttribute("jakarta.servlet.error.message");
        return String.format("Error occurred with status code %d and message: %s", statusCode, errorMessage);
    }

    public String getErrorPath() {
        return "/error";
    }
}
