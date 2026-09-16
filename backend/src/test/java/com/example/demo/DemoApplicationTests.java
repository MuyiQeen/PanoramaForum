package com.example.demo;

import com.example.demo.service.AuthService;
import com.example.demo.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	AuthServiceImpl authService;
	@Test
	void test() throws Exception {
		authService.generateId();
	}



}
