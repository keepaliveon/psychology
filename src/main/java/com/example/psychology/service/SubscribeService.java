package com.example.psychology.service;

import com.example.psychology.entity.Student;
import com.example.psychology.entity.Subscribe;
import com.example.psychology.entity.Teacher;
import com.example.psychology.repository.StudentRepository;
import com.example.psychology.repository.SubscribeRepository;
import com.example.psychology.repository.TeacherRepository;
import com.example.psychology.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class SubscribeService {

    @Resource
    private SubscribeRepository subscribeRepository;

    @Resource
    private StudentRepository studentRepository;

    @Resource
    private TeacherRepository teacherRepository;

    public void create(String studentId, String staffId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Teacher> teacherOptional = teacherRepository.findById(staffId);
        if (studentOptional.isPresent() && teacherOptional.isPresent()) {
            Subscribe subscribe = new Subscribe();
            subscribe.setId(UUIDUtils.SerialNum());
            subscribe.setCreateTime(new Date());
            subscribe.setStudent(studentOptional.get());
            subscribe.setTeacher(teacherOptional.get());
            subscribeRepository.save(subscribe);
        }
    }

    public void remove(String id) {
        subscribeRepository.deleteById(id);
    }

}
