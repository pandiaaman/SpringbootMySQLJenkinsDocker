package com.springSQLJenkinsDocker.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springSQLJenkinsDocker.serviceImpl.StudentServiceImpl;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Slf4j
class StudentControllerTest {

	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	private StudentServiceImpl studentService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		log.info("");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}
	
//	@Test
//	void StudentController_AddStudent_ReturnSavedStudentEntityResponse() throw Exception{
//		
//	}

	
	
	
	

	
}
