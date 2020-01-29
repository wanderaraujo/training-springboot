package com.araujo.training.javaclient;

import com.araujo.training.model.PageableResponse;
import com.araujo.training.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class JavaClientDAO {

    RestTemplate restTemplate = new RestTemplateBuilder()
            .rootUri("http://localhost:8080/v1/students")
            .basicAuthentication("user_01", "123")
            .build();

    RestTemplate restTemplateAdmin = new RestTemplateBuilder()
            .rootUri("http://localhost:8080/v1/students/admin")
            .basicAuthentication("user_01", "123")
            .build();

    public Student findById(Long id){
       return restTemplate.getForObject("/{id}", Student.class, id);
    }

    public List<Student> listAll(){
        ResponseEntity<PageableResponse<Student>> exchange = restTemplate.exchange("/",
                HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponse<Student>>() {});

        return exchange.getBody().getContent();
    }

    public Student save(Student student){
        ResponseEntity<Student> exchangePost = restTemplateAdmin.exchange("/",
                HttpMethod.POST, new HttpEntity<>(student, createJsonHeader()), Student.class );

        return exchangePost.getBody();
    }

    private static HttpHeaders createJsonHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
