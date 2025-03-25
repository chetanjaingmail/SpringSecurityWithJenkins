package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Get the error status code
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        
        if (statusCode != null) {
            if (statusCode == 404) {
                return "404"; // Returns 404.html from templates or static folder
            }
            else if (statusCode == 403) {
                return "403"; // Returns 404.html from templates or static folder
            }
        }
        
        
        return "error"; // Fallback error page
    }
}
