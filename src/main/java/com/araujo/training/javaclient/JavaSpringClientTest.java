package com.araujo.training.javaclient;

import com.araujo.training.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class JavaSpringClientTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/students")
                .basicAuthentication("user_01", "123")
                .build();

        Student st = restTemplate.getForObject("/{id}", Student.class, 11);
        ResponseEntity<Student> forEntity = restTemplate.getForEntity("/{id}", Student.class, 11);

        Student[] sts = restTemplate.getForObject("/", Student[].class);

        System.out.println(st.toString());
        System.out.println(forEntity.getBody());
        System.out.println(forEntity.getStatusCode());
        System.out.println(forEntity.getHeaders());

//        System.out.println(Arrays.toString(sts));

        ResponseEntity<List<Student>> exchange = restTemplate.exchange("/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {});

        System.out.println(exchange.getBody());
    }
}
