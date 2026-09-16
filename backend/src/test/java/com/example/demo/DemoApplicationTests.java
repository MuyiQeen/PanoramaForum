package com.example.demo;

import com.example.demo.dao.entity.IdPoolEntity;
import com.example.demo.dao.entity.UserEntity;
import com.example.demo.service.impl.AuthServiceImpl;
import com.example.demo.utils.IdUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	AuthServiceImpl authService;

	@Test
	void contextLoads() {
	}

	@Test
	void testRandIdGenerator() throws Exception {

		authService.generateId();


	}

}
