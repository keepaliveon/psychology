package com.example.psychology.service;

import com.example.psychology.entity.Student;
import com.example.psychology.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    @Resource
    private StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> list() {
        return studentRepository.findAll();
    }

    public Student findById(String id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.orElse(null);
    }

    public Student signIn(String id, String password) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }
}
