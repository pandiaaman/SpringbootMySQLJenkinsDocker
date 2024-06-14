package com.springSQLJenkinsDocker.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springSQLJenkinsDocker.dto.DTOConverterImpl;
import com.springSQLJenkinsDocker.dto.StudentRequestDTO;
import com.springSQLJenkinsDocker.dto.StudentResponseDTO;
import com.springSQLJenkinsDocker.dto.StudentUpdateRequestDTO;
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
	public StudentResponseDTO saveStudent(StudentRequestDTO studentRequest) throws RuntimeException {
		log.info("StudentServiceImpl::saveStudent :: adding a student to the db...");
		
		Student student = dtoMapper.studentRequestDtoToStudent(studentRequest);
		
		Student savedStudent =  this.repository.save(student);
		
		StudentResponseDTO studentResponse = this.dtoMapper.studentToStudentResponseDTO(savedStudent);
		
		return studentResponse;
	}

	@Override
	public StudentResponseDTO getStudent(String studentId) throws RuntimeException {
		log.info("StudentServiceImpl::getStudent :: getting student with id: " + studentId);
		
		Student studentFetched = this.repository.findById(studentId).orElseThrow(() -> new RuntimeException("student not found"));
		StudentResponseDTO resultStudent = this.dtoMapper.studentToStudentResponseDTO(studentFetched);
		
		return resultStudent;
	}

	@Override
	public StudentResponseDTO deleteStudent(String studentId) throws RuntimeException {
		log.info("deleting the student... " + studentId);
		
		
		StudentResponseDTO deletedStudent = this.getStudent(studentId);
		this.repository.deleteById(studentId);
				
		
		return deletedStudent;
	}

	@Override
	public List<StudentResponseDTO> getAllStudents() throws RuntimeException {
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

	@Override
	public List<StudentResponseDTO> findByStudentName(String studentName) throws RuntimeException {
		log.info("finding all students with the given name...");
		
		List<Student> studentsWithGivenName = this.repository.findByStudentName(studentName);
		
		
		if(studentsWithGivenName.size() == 0) {
			throw new RuntimeException("No students found with given name");
		}
		
		List<StudentResponseDTO> resultStudentList = new ArrayList<>();
		
		for(Student student : studentsWithGivenName) {
			resultStudentList.add(this.dtoMapper.studentToStudentResponseDTO(student));
		}
		return resultStudentList;
	}

	@Override
	public List<StudentResponseDTO> findStudentsWithSameMarks(double marks) throws RuntimeException{
		log.info("finding studnets with the same marks " + marks);
		
		List<Student> studentsWithSameMarks = this.repository.studentsWithSameMarks(marks);
		
		if(studentsWithSameMarks.size() == 0) {
			throw new RuntimeException("No students found with given name");
		}
		
		List<StudentResponseDTO> resultStudentList = new ArrayList<>();
		
		for(Student student : studentsWithSameMarks) {
			resultStudentList.add(this.dtoMapper.studentToStudentResponseDTO(student));
		}
		
		return resultStudentList;
	}

	@Override
	public StudentResponseDTO updateStudent(StudentUpdateRequestDTO student) throws RuntimeException{
		log.info("updating the student object");
		
		//for updating, spring boot automatically does the work using save if id exists in database
		Student updatedStudent = this.repository.save(this.dtoMapper.studentUpdateRequestDtoToStudent(student));
		
		StudentResponseDTO responseStudent = this.dtoMapper.studentToStudentResponseDTO(updatedStudent);
		
		return responseStudent;
	}

	
}
