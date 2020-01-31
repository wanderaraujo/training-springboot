package com.araujo.training;

import com.araujo.training.model.Student;
import com.araujo.training.repository.StudentRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.invoke.empty.Empty;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersistData(){
        Student student = new Student("wander", "wander@das.com");
        this.studentRepository.save(student);
        assertNotNull(student.getId());
        assertEquals(student.getName(), "wander");
        assertEquals(student.getEmail(), "wander@das.com");
    }

    @Test
    public void deleteShouldRemoveData(){
        Student student = new Student("Bruno", "bruno@das.com");
        this.studentRepository.save(student);
        this.studentRepository.delete(student);
        assertEquals(Optional.empty(), this.studentRepository.findById(student.getId()));
    }

    @Test
    public void updateShouldPersistData(){
        Student student = new Student("wander", "wander@das.com");
        this.studentRepository.save(student);
        student.setName("wanderModified");
        student.setEmail("wanderModified@fmil.com");
        student =  this.studentRepository.save(student);
        assertEquals(student.getName(), "wanderModified");
        assertEquals(student.getEmail(), "wanderModified@fmil.com");
    }

    @Test
    public void findByNameIgnoreCaseContaingShouldPersistData(){
        Student student = new Student("Wander", "wander@das.com");
        Student student2 = new Student("wander", "wander@das.com");
        this.studentRepository.save(student);
        this.studentRepository.save(student2);
        List<Student> studentRepository = this.studentRepository.findByNameIgnoreCaseContaining(student.getName());
        assertEquals(studentRepository.size(), 2);
    }


    @Test
    public void createWhenNameIsNullShouldThrowConstraintViolationException(){
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Email do estudante nao pode ser em branco");
        this.studentRepository.save(new Student());
    }

    @Test
    public void createWhenEmailIsNullShouldThrowConstraintViolationException(){
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Email do estudante nao pode ser em branco");
        Student student = new Student();
        student.setName("wander");
        this.studentRepository.save(student);
    }
}
