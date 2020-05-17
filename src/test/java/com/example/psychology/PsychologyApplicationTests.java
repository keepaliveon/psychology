package com.example.psychology;

import com.alibaba.fastjson.JSON;
import com.example.psychology.Comman.Message;
import com.example.psychology.service.StudentService;
import com.example.psychology.service.TeacherService;
import com.google.gson.Gson;
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
		Message message = JSON.parseObject("{\"to\":\"201612211415\",\"text\":\"ddd\"}", Message.class);
		System.out.println(message);
		System.out.println("{\"to\":\"201612211415\",\"text\":\"ddd\"}");
		Gson gson = new Gson();
		Message message1 = gson.fromJson("{\"to\":\"201612211415\",\"text\":\"ddd\"}", Message.class);
		System.out.println(message1);
	}

}
