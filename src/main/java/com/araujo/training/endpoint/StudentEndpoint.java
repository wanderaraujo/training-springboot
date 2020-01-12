package com.araujo.training.endpoint;

import com.araujo.training.error.CustomErrorType;
import com.araujo.training.model.Student;
import com.araujo.training.repository.StudentRepository;
import com.araujo.training.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

    @Autowired
    private final StudentRepository studentDao;

    @Autowired // @Autowired realiza a injecao de dependencia
    private DateUtil dateUtil;

    public StudentEndpoint(StudentRepository studentDao) {
        this.studentDao = studentDao;
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<?> listAll(){
        System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(studentDao.findAll(), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/{id}")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
        Optional<Student> student = studentDao.findById(id);

        if(student.equals(Optional.empty()))
            return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<?> insertStudent(@RequestBody Student student){
        studentDao.save(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    //@RequestMapping(method = RequestMethod.DELETE)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id){
        getStudentById(id);
        studentDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody Student student){
        getStudentById(student.getId());
        studentDao.save(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

}
