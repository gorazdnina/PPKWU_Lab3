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

import java.nio.charset.Charset;

@Service
public class StringService {
    @Autowired
    CountClient client;
    public ResponseEntity<byte[]> countString(CountRequest countRequest) {
        CountResponse clientResponse = client.stringStatistics(countRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        if(countRequest.getFormat().equals("txt")) {
            httpHeaders.setContentType(MediaType.TEXT_PLAIN);
            return new ResponseEntity<>(txtFile(clientResponse), httpHeaders , HttpStatus.OK);
        } else if(countRequest.getFormat().equals("json")) {
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(jsonFile(clientResponse), httpHeaders , HttpStatus.OK);
        } else if(countRequest.getFormat().equals("xml")) {
            httpHeaders.setContentType(MediaType.TEXT_XML);
            return new ResponseEntity<>(xmlFile(clientResponse), httpHeaders , HttpStatus.OK);
        } else if(countRequest.getFormat().equals("csv")) {
            httpHeaders.setContentType( new MediaType("text", "csv", Charset.forName("utf-8")) );
            return new ResponseEntity<>(csvFile(clientResponse), httpHeaders , HttpStatus.OK);
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

    private byte[] jsonFile(CountResponse clientResponse) {
        String str = """ 
        {
        "Uppercase letters": %d,
        "Lowercase letters": %d,
        "Digits": %d,
        "Special signs": %d,
        "Number of occurrences": %d
        }
        """;
        return String.format(str, clientResponse.getUppercaseLetters(),clientResponse.getLowercaseLetters(),
                clientResponse.getDigits(), clientResponse.getSpecialSigns(), clientResponse.getNumOfOccurrences()).getBytes();

    }

    private byte[] xmlFile(CountResponse clientResponse) {
        String str = """
        <countResponse>
        <Uppercase_letters>%d</Uppercase_letters>,
        <Lowercase_letters>%d</Lowercase_letters>,
        <Digits>%d</Digits>,
        <Special_signs>%d</Special_signs>,
        <Number_of_occurrences>%d</Number_of_occurrences>
        </countResponse>
        """;
        return String.format(str, clientResponse.getUppercaseLetters(),clientResponse.getLowercaseLetters(),
                clientResponse.getDigits(), clientResponse.getSpecialSigns(), clientResponse.getNumOfOccurrences()).getBytes();

    }

    private byte[] csvFile(CountResponse clientResponse) {
        String str = """
        Uppercase_Letters,Lowercase_Letters, Digits, Special_Signs, Number_Of_Occurrences
        %d,%d,%d,%d,%d
        """;
        return String.format(str, clientResponse.getUppercaseLetters(),clientResponse.getLowercaseLetters(),
                clientResponse.getDigits(), clientResponse.getSpecialSigns(), clientResponse.getNumOfOccurrences()).getBytes();

    }
}
