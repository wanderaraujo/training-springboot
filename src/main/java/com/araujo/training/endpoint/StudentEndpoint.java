package com.araujo.training.endpoint;

import com.araujo.training.error.CustomErrorType;
import com.araujo.training.error.ResourceNotFoundException;
import com.araujo.training.model.Student;
import com.araujo.training.repository.StudentRepository;
import com.araujo.training.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("v1/students")
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
    public ResponseEntity<?> listAll(Pageable pageable){
        System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(studentDao.findAll(), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/{id}")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
        verifyIfStudentExists(id);
        return new ResponseEntity<>(studentDao.findById(id), HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<?> insertStudent(@Valid @RequestBody Student student){
        studentDao.save(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PostMapping(path = "/forceErrorTransaction")
    @Transactional // se a execao for do tipo unchecked (RuntimeException), nao precisa declarar o tipo de rollback
    //@Transactional(rollbackFor = Exception.class) // se a execao for do tipo checked, necessario declarar qual o tipo de execao para o rollback
    public ResponseEntity<?> insertStudentError(@RequestBody Student student){
        studentDao.save(student);
        student.setId(null);
        studentDao.save(student);
        if(true)
            throw new RuntimeException("Test transaction");
        studentDao.save(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    //@RequestMapping(method = RequestMethod.DELETE)
    @DeleteMapping(path = "/admin/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id){
        verifyIfStudentExists(id);
        studentDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody Student student){
        verifyIfStudentExists(student.getId());
        return new ResponseEntity<>(studentDao.save(student), HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> findStudentByName(@PathVariable String name){
        return new ResponseEntity<>(studentDao.findByNameIgnoreCaseContaining (name), HttpStatus.OK);
    }

    private void verifyIfStudentExists(Long id){
        Optional<Student> student = studentDao.findById(id);

        if(student.equals(Optional.empty()))
            throw new ResourceNotFoundException("Student not found  " + id);
    }

}
