package com.example.Lab3.controller;

import com.example.Lab3.dto.CountRequest;
import com.example.Lab3.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StringController {
    @Autowired
    private StringService stringService;
    @PostMapping("/request")
    public ResponseEntity<byte[]> getController(@RequestBody CountRequest countRequest) {
        return stringService.countString(countRequest);
    }

}
