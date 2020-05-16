package com.example.psychology;

import com.example.psychology.entity.Student;
import com.example.psychology.entity.Teacher;
import com.example.psychology.service.StudentService;
import com.example.psychology.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class PsychologyApplicationTests {

	@Resource
	private TeacherService teacherService;

	@Resource
	private StudentService studentService;

	@Test
	void contextLoads() {
	}

	@Test
	void t1() {

	}

}
