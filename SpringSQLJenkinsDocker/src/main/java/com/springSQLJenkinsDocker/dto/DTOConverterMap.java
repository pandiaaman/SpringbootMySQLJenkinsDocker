package com.springSQLJenkinsDocker.dto;

import com.springSQLJenkinsDocker.entities.Student;

public interface DTOConverterMap {

	public StudentResponseDTO studentToStudentResponseDTO(Student student);
	
	public Student studentRequestDtoToStudent(StudentRequestDTO studentRequestDTO);
	
	public Student studentUpdateRequestDtoToStudent(StudentUpdateRequestDTO studentUpdateRequestDTO);
}
