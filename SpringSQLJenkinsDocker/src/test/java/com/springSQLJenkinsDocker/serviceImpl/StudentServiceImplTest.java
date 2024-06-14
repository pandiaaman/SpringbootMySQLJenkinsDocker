package com.springSQLJenkinsDocker.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.springSQLJenkinsDocker.dto.DTOConverterImpl;
import com.springSQLJenkinsDocker.dto.StudentRequestDTO;
import com.springSQLJenkinsDocker.dto.StudentResponseDTO;
import com.springSQLJenkinsDocker.entities.Student;
import com.springSQLJenkinsDocker.repositories.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
	
	
	@InjectMocks
	private StudentServiceImpl studentService;

	@Mock
	private StudentRepository repositoryMock;
	
	@Mock
	private DTOConverterImpl dtoMappingMock;
	
	@Autowired
	private DTOConverterImpl dtoMapper;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		log.info("StudentServiceImplTest is running...");
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		log.info("StudentServiceImplTest has run!!!");
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void StudentService_SaveStudent_ReturnsSavedStudentResponseDTO() {
		log.info("testing save Student...");
		
		//Arrange
		//expected output from repository save method
		StudentResponseDTO expectedOutput = StudentResponseDTO.builder().studentId("someid")
				.studentName("Test Name")
				.studentMarks(50.0)
				.build();
		
		
		
		StudentRequestDTO studentRequestOb = new StudentRequestDTO("Test Name",50.0);
		
		//Mocks for below lines of code:
		//mock for Student student = dtoMapper.studentRequestDtoToStudent(studentRequest);
//		when(this.dtoMappingMock.studentRequestDtoToStudent(any())).thenReturn(new Student());
		//mock for Student savedStudent =  this.repository.save(student);
//		when(this.repositoryMock.save(any())).thenReturn(new Student());
		//mock for StudentResponseDTO studentResponse = this.dtoMapper.studentToStudentResponseDTO(savedStudent);
		when(this.dtoMappingMock.studentToStudentResponseDTO(any())).thenReturn(expectedOutput);
		
		//Act
		StudentResponseDTO testOutput = this.studentService.saveStudent(studentRequestOb);
		
		log.info("actual output" + testOutput);
		log.info("expected output" + expectedOutput);
		
		//Assert
		assertEquals(testOutput, expectedOutput);
		
//		fail("Not yet implemented");
	}

//	@Test
//	void testGetStudent() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteStudent() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void StudentService_GetAllStudents_ReturnListOfStudentResponseDTO() {
//		log.info("testing getAll students service...");
//		
//		//Arrange
////		List<StudentResponseDTO> expectedResponseList = new ArrayList<>();
////		
////		StudentResponseDTO stud1 = StudentResponseDTO.builder().studentId("stud1").studentName("Aman").studentMarks(10).build();
////		StudentResponseDTO stud2 = StudentResponseDTO.builder().studentId("stud2").studentName("Anand").studentMarks(100).build();
////		StudentResponseDTO stud3 = StudentResponseDTO.builder().studentId("stud3").studentName("Vaibhav").studentMarks(70).build();
////		StudentResponseDTO stud4 = StudentResponseDTO.builder().studentId("stud4").studentName("Sumit").studentMarks(50).build();
////		
////		expectedResponseList.add(stud1);
////		expectedResponseList.add(stud2);
////		expectedResponseList.add(stud3);
////		expectedResponseList.add(stud4);
//		
//		this.studentService.saveStudent(new StudentRequestDTO("hello",50.0));
//		
//		//Act
//		when(this.repositoryMock.findAll()).thenReturn(new ArrayList<Student>());
//		when(this.dtoMappingMock.studentToStudentResponseDTO(any())).thenReturn(new StudentResponseDTO());
//		
//		List<StudentResponseDTO> actualOutput = this.studentService.getAllStudents();
//		//Assert
//		assertThat(actualOutput).isNotNull();
//	}

}
