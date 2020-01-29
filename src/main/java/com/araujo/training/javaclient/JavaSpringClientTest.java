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
        Student studentUpdate = new Student();

//        studentPost.setEmail("taiana@gmai.com");
//        studentPost.setName("Taiana");
//
        studentUpdate.setId(new Long(4));
        studentUpdate.setEmail("taiana@gmai.com");
        studentUpdate.setName("Taiana dasdaskjdhadahdjahdjkahdja");

//        System.out.println(dao.save(studentPost));
//        System.out.println(dao.listAll());
//        System.out.println(dao.findById(new Long(16)));
//        dao.delete(new Long(21));
        dao.update(studentUpdate);

    }

}
