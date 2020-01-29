package com.araujo.training.javaclient;

import com.araujo.training.model.PageableResponse;
import com.araujo.training.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class JavaSpringClientTest {

    public static void main(String[] args) {

        JavaClientDAO dao = new JavaClientDAO();

        Student studentPost = new Student();
        studentPost.setEmail("taiana@gmai.com");
        studentPost.setName("Taiana");

        System.out.println(dao.save(studentPost));
        System.out.println(dao.listAll());
        System.out.println(dao.findById(new Long(16)));

    }

}
