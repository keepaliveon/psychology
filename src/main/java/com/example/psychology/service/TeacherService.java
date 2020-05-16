package com.example.psychology.service;

import com.example.psychology.entity.Teacher;
import com.example.psychology.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherService {

    @Resource
    private TeacherRepository teacherRepository;

    public Teacher save(Teacher student) {
        return teacherRepository.save(student);
    }

    public List<Teacher> list() {
        return teacherRepository.findAll();
    }

    public Teacher findById(String id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isPresent()) {
            return teacherOptional.get();
        } else {
            return null;
        }
    }

    public Teacher signIn(String id, String password) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isPresent()) {
            Teacher teacher = teacherOptional.get();
            if (teacher.getPassword().equals(password)) {
                return teacher;
            }
        }
        return null;
    }
}
