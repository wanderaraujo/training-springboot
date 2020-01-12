package com.araujo.training.endpoint;

import com.araujo.training.error.CustomErrorType;
import com.araujo.training.model.Student;
import com.araujo.training.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

    @Autowired // @Autowired realiza a injecao de dependencia
    private DateUtil dateUtil;

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<?> listAll(){
        System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/{id}")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id){
        Student student = new Student();
        student.setId(id);
        int index = Student.studentList.indexOf(student);
        if(index == -1)
            return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(Student.studentList.get(index), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<?> insertStudent(@RequestBody Student student){
        Student.studentList.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    //@RequestMapping(method = RequestMethod.DELETE)
    @DeleteMapping
    public ResponseEntity<?> deleteStudent(@RequestBody Student student){
        Student.studentList.remove(student);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody Student student){
        Student.studentList.remove(student);
        Student.studentList.add(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

}
