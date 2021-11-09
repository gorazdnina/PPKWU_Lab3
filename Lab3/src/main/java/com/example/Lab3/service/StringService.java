package com.example.Lab3.service;

import com.example.Lab3.client.CountClient;
import com.example.Lab3.dto.CountRequest;
import com.example.Lab3.dto.CountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StringService {
    @Autowired
    CountClient client;
    public ResponseEntity<byte[]> countString(CountRequest countRequest) {
        CountResponse clientResponse = client.stringStatistics(countRequest);
        if(countRequest.getFormat().equals("txt")) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.TEXT_PLAIN);
            return new ResponseEntity<>(txtFile(clientResponse), httpHeaders , HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    private byte[] txtFile(CountResponse clientResponse) {
        String str = """
        Uppercase letters: %d,
        Lowercase letters: %d,
        Digits: %d,
        Special signs: %d,
        Number of occurrences: %d
        """;
        return String.format(str, clientResponse.getUppercaseLetters(),clientResponse.getLowercaseLetters(),
                clientResponse.getDigits(), clientResponse.getSpecialSigns(), clientResponse.getNumOfOccurrences()).getBytes();

    }
}
