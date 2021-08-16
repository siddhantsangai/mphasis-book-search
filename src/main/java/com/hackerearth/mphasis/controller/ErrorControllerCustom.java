package com.hackerearth.mphasis.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorControllerCustom implements ErrorController {
    @RequestMapping("/error")
    public ResponseEntity handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer code = Integer.valueOf(status.toString());

            if(code == HttpStatus.NOT_FOUND.value()) {
                return ResponseEntity.notFound().build();
            }
            else if(code == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return ResponseEntity.internalServerError().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
