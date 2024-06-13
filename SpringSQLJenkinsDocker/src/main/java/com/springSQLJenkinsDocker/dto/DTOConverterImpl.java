package com.springSQLJenkinsDocker.dto;

import org.springframework.stereotype.Component;

import com.springSQLJenkinsDocker.entities.Student;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DTOConverterImpl implements DTOConverterMap{@Override
	
	public StudentResponseDTO studentToStudentResponseDTO(Student student) {
		log.info("DTOConverterImpl::studentToStudentResponseDTO");
		
		StudentResponseDTO studentResponse = new StudentResponseDTO();
		
		studentResponse.setStudentId(student.getStudentId());
		studentResponse.setStudentName(student.getStudentName());
		studentResponse.setStudentMarks(student.getStudentMarks());
		
		return studentResponse;
	}

	@Override
	public Student studentRequestDtoToStudent(StudentRequestDTO studentRequestDTO) {
		log.info("DTOConverterImpl::studentRequestDtoToStudent");
		
		Student student = new Student();
		
		student.setStudentName(studentRequestDTO.getStudentName());
		student.setStudentMarks(studentRequestDTO.getStudentMarks());
		
		return student;
	}

	
}
