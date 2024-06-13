package com.springSQLJenkinsDocker.services;

import java.util.List;

import com.springSQLJenkinsDocker.dto.StudentRequestDTO;
import com.springSQLJenkinsDocker.dto.StudentResponseDTO;

public interface StudentService {

	//POST
	public StudentResponseDTO saveStudent(StudentRequestDTO studentRequest);
	
	//GET
	public StudentResponseDTO getStudent(String studentId);
	
	//DELETE
	public StudentResponseDTO deleteStudent(String studentId);
	
	//GET ALL
	public List<StudentResponseDTO> getAllStudents();
}
