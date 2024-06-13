package com.springSQLJenkinsDocker.serviceImpl;

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
	
	private static List<Student> databaseData = new ArrayList<>();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		log.info("StudentServiceImplTest is running...");
		
		
		
		
		
		Student stud1 = new Student("UID1", "Aman", 10);
		Student stud2 = new Student("UID2", "Divyam", 50);
		Student stud3 = new Student("UID3", "Anand", 100);
		Student stud4 = new Student("UID4", "Ayaz", 80);
		Student stud5 = new Student("UID5", "Sumit", 90);
		
		databaseData.add(stud1);
		databaseData.add(stud2);
		databaseData.add(stud3);
		databaseData.add(stud4);
		databaseData.add(stud5);
		
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
	void testSaveStudent() {
		log.info("testing save Student...");
		
		//expected output from repository save method
		StudentResponseDTO expectedOutput = StudentResponseDTO.builder().studentId("someid")
				.studentName("Test Name")
				.studentMarks(50.0)
				.build();
		
		
		
		StudentRequestDTO studentRequestOb = new StudentRequestDTO("Test Name",50.0);
		
		//Mocks for below lines of code:
		//mock for Student student = dtoMapper.studentRequestDtoToStudent(studentRequest);
		when(this.dtoMappingMock.studentRequestDtoToStudent(any())).thenReturn(new Student());
		//mock for Student savedStudent =  this.repository.save(student);
		when(this.repositoryMock.save(any())).thenReturn(new Student());
		//mock for StudentResponseDTO studentResponse = this.dtoMapper.studentToStudentResponseDTO(savedStudent);
		when(this.dtoMappingMock.studentToStudentResponseDTO(any())).thenReturn(expectedOutput);
		
		
		StudentResponseDTO testOutput = this.studentService.saveStudent(studentRequestOb);
		
		log.info("actual output" + testOutput);
		log.info("expected output" + expectedOutput);
		
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
//	void testGetAllStudents() {
//		
//		//preparing the expected output
//		List<StudentResponseDTO> expectedOutput = new ArrayList<>();
//		for(Student stud: databaseData) {
//			expectedOutput.add(new StudentResponseDTO("1","aman",5));
//		}
//		
//		//mock for List<Student> allStudents = this.repository.findAll();
//		when(this.repositoryMock.findAll()).thenReturn(databaseData);
//		
//		//mock for resultStudentList.add(this.dtoMapper.studentToStudentResponseDTO(student));
//		when(this.dtoMapper.studentToStudentResponseDTO(any())).thenReturn(new StudentResponseDTO("1","aman",5));
//		
//		List<StudentResponseDTO> actualStudentList =  this.studentService.getAllStudents();
//		
//		assertEquals(expectedOutput, actualStudentList);
////		fail("Not yet implemented");
//	}

}
