package com.springSQLJenkinsDocker.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springSQLJenkinsDocker.dto.DTOConverterImpl;
import com.springSQLJenkinsDocker.dto.StudentRequestDTO;
import com.springSQLJenkinsDocker.dto.StudentResponseDTO;
import com.springSQLJenkinsDocker.entities.Student;
import com.springSQLJenkinsDocker.repositories.StudentRepository;
import com.springSQLJenkinsDocker.services.StudentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private DTOConverterImpl dtoMapper;
	
	@Override
	public StudentResponseDTO saveStudent(StudentRequestDTO studentRequest) {
		log.info("StudentServiceImpl::saveStudent :: adding a student to the db...");
		
		Student student = dtoMapper.studentRequestDtoToStudent(studentRequest);
		
		Student savedStudent =  this.repository.save(student);
		
		StudentResponseDTO studentResponse = this.dtoMapper.studentToStudentResponseDTO(savedStudent);
		
		return studentResponse;
	}

	@Override
	public StudentResponseDTO getStudent(String studentId) {
		log.info("StudentServiceImpl::getStudent :: getting student with id: " + studentId);
		
		Student studentFetched = this.repository.findById(studentId).orElseThrow(() -> new RuntimeException("student not found"));
		StudentResponseDTO resultStudent = this.dtoMapper.studentToStudentResponseDTO(studentFetched);
		
		return resultStudent;
	}

	@Override
	public StudentResponseDTO deleteStudent(String studentId) {
		log.info("deleting the student... " + studentId);
		
		
		StudentResponseDTO deletedStudent = this.getStudent(studentId);
		this.repository.deleteById(studentId);
				
		
		return deletedStudent;
	}

	@Override
	public List<StudentResponseDTO> getAllStudents() {
		log.info("getting all students...");
		List<Student> allStudents = this.repository.findAll();
		
		if(allStudents.size() == 0) {
			throw new RuntimeException("No students found");
		}
		
		List<StudentResponseDTO> resultStudentList = new ArrayList<>();
		
		for(Student student : allStudents) {
			resultStudentList.add(this.dtoMapper.studentToStudentResponseDTO(student));
		}
		return resultStudentList;
	}

	
}
