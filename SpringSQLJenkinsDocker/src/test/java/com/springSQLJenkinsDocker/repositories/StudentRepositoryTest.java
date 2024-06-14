package com.springSQLJenkinsDocker.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.springSQLJenkinsDocker.entities.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class StudentRepositoryTest {

	//We will be using the h2 database to replicate the database
	
	
	@Autowired
	private StudentRepository repository;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		log.info("StudentRepository testing started...");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		log.info("StudentRepository testing finished!!!");
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void StudentRepository_OnSave_ReturnsSavedStudent() {
		log.info("testing the repository save method");
		
		//Arrange : prepare what you are going to pass in the method to test
		Student student = Student.builder().studentName("some name").studentMarks(10).build();
		
		//Act : pass the prepared object in the method to test
		Student savedStudent = repository.save(student);
		
		log.info("student id" + savedStudent.getStudentId());
		
		//Assert
		assertThat(savedStudent).isNotNull();
		assertThat(savedStudent.getStudentId()).isNotNull();
		
		
	}
	
	@Test
	void StudentRepository_FindAll_ReturnsAllStudents() {
		log.info("testing the repository findAll method");
		
		//Arrange
		Student st1 = Student.builder().studentName("student1").studentMarks(10).build();
		Student st2 = Student.builder().studentName("student2").studentMarks(5).build();
		
		repository.save(st1);
		repository.save(st2);
		
		//Act
		List<Student> allStudents = repository.findAll();
		
		//Assert
		assertThat(allStudents).isNotNull();
		assertThat(allStudents.size()).isEqualTo(2);
	}
	
	@Test
	void StudentRepository_FindById_ReturnsStudentWithGivenId() {
		log.info("testing the find by id mehtod of the student repository");
		
		//Arrange
		Student stud = Student.builder().studentName("Aman").studentMarks(50).build();
		
		Student savedStud = repository.save(stud);
		
		String idToFind = savedStud.getStudentId();
		
		//Act
		Student fetchedStud = repository.findById(idToFind).orElseThrow(() -> new RuntimeException("ID not found"));
		
		//Assert
		assertThat(fetchedStud).isNotNull();
		assertThat(fetchedStud.getStudentName()).isEqualTo(savedStud.getStudentName());
		
	}
	
	@Test
	void StudentRepository_DeleteById_StudentShouldBeDeleted() {
		log.info("testing the delete method of the JPArepository");
		
		//Arrange
		Student st1 = Student.builder().studentName("student1").studentMarks(10).build();
		Student st2 = Student.builder().studentName("student2").studentMarks(5).build();
		
		repository.save(st1);
		repository.save(st2);
		
		String idToDelete = st1.getStudentId();
		
		//Act
		repository.deleteById(idToDelete);
		
		//Assert
		List<Student> stuentsinDb = repository.findAll();
		
		assertThat(stuentsinDb.size()).isEqualTo(1);
	}
	

	@Test
	void studentRepository_FindByName_ReturnStudentsWithSameName() {
		log.info("testing the findByName method of the student repository...");
		
		//Arrange
		Student s1 = Student.builder().studentName("Aman").studentMarks(10).build();
		Student s2 = Student.builder().studentName("Aman").studentMarks(10).build();
		Student s3 = Student.builder().studentName("Divyam").studentMarks(10).build();
		
		repository.save(s1);
		repository.save(s2);
		repository.save(s3);
		
		//Act
		List<Student> studentsWithNameAman = repository.findByStudentName("Aman");
		
		//Assert
		assertThat(studentsWithNameAman).isNotNull();
		assertThat(studentsWithNameAman.size()).isEqualTo(2);
	}
	
	@Test
	void StudentRepository_FindStudentsWithSameMarks_ReturnsStudentsWithSameMarks() {
		log.info("testing the students with same marks...");
		
		//Arrange
		Student s1 = Student.builder().studentName("Aman").studentMarks(10).build();
		Student s2 = Student.builder().studentName("Vaibhav").studentMarks(10).build();
		Student s3 = Student.builder().studentName("Divyam").studentMarks(50).build();
		Student s4 = Student.builder().studentName("Sumit").studentMarks(10).build();
		Student s5 = Student.builder().studentName("Kunal").studentMarks(60).build();
		Student s6 = Student.builder().studentName("Divyam").studentMarks(10).build();
		
		repository.save(s1);
		repository.save(s2);
		repository.save(s3);
		repository.save(s4);
		repository.save(s5);
		repository.save(s6);
		
		//Act
		List<Student> studentsWithSameMarks = repository.studentsWithSameMarks(10);
		
		//Assert
		assertThat(studentsWithSameMarks).isNotNull();
		assertThat(studentsWithSameMarks.size()).isEqualTo(4);
	}
}
