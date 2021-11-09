package com.example.Lab3.client;

import com.example.Lab3.dto.CountRequest;
import com.example.Lab3.dto.CountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CountClient {
    private RestTemplate restTemplate = new RestTemplate();

    public CountResponse stringStatistics(CountRequest statisticsRequest) {
        RequestEntity<CountRequest> request = RequestEntity
                .post("http://localhost:8080/request").body(statisticsRequest);

        return restTemplate.exchange(request, CountResponse.class).getBody();
    }
}
