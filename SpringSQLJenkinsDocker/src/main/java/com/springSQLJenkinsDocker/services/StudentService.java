package com.springSQLJenkinsDocker.services;

import java.util.List;

import com.springSQLJenkinsDocker.dto.StudentRequestDTO;
import com.springSQLJenkinsDocker.dto.StudentResponseDTO;
import com.springSQLJenkinsDocker.dto.StudentUpdateRequestDTO;

public interface StudentService {

	//POST
	public StudentResponseDTO saveStudent(StudentRequestDTO studentRequest);
	
	//GET
	public StudentResponseDTO getStudent(String studentId);
	
	//DELETE
	public StudentResponseDTO deleteStudent(String studentId);
	
	//GET ALL
	public List<StudentResponseDTO> getAllStudents();
	
	//FIND BY STUDENT NAME
	public List<StudentResponseDTO> findByStudentName(String studentName);
	
	//STUDENTS WITH SAME MARKS
	public List<StudentResponseDTO> findStudentsWithSameMarks(double marks);
	
	//UPDATE STUDENT
	public StudentResponseDTO updateStudent(StudentUpdateRequestDTO student);
}
