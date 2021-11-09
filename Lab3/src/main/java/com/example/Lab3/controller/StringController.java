package com.example.Lab3.controller;

import com.example.Lab3.dto.CountRequest;
import com.example.Lab3.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StringController {
    @Autowired
    private StringService stringService;
    @GetMapping("/request")
    public ResponseEntity<CountRequest> getController(@RequestBody CountRequest countRequest) {
        return ResponseEntity.ok(stringService.countString(countRequest));
    }

}
