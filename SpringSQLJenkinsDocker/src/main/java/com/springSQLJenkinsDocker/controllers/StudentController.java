package com.springSQLJenkinsDocker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springSQLJenkinsDocker.dto.StudentRequestDTO;
import com.springSQLJenkinsDocker.dto.StudentResponseDTO;
import com.springSQLJenkinsDocker.dto.StudentUpdateRequestDTO;
import com.springSQLJenkinsDocker.services.StudentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
	
	@Autowired
	private StudentService service;

	/*
	 * 
	 */
	@GetMapping(value = "/byid/{studentId}",
			produces = {"application/json","application/xml"})
	private ResponseEntity<StudentResponseDTO> getStudent(@PathVariable String studentId){
		log.info("StudentController::getStudent:: fetching a student with id: " + studentId);
		
		try {
			StudentResponseDTO fetchedStudent = this.service.getStudent(studentId);
			
			return ResponseEntity.status(HttpStatus.OK).body(fetchedStudent);
		}catch(Exception e) {
			log.error("ERROR:: exception occured " + e);
			e.printStackTrace();
		}
		
		log.info("sending a bad request...");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	/*
	 * 
	 */
	@GetMapping(value = "/all",
			produces = {"application/json","application/xml"})
	private ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
		log.info("StudentController::getAllStudents:: getting all the students...");
		
		
		try {
			List<StudentResponseDTO> allFetchedStudents = this.service.getAllStudents();
			
			return ResponseEntity.status(HttpStatus.OK).body(allFetchedStudents);
		}catch(Exception e) {
			log.error("ERROR:: exception occured while fetching all students" + e);
			e.printStackTrace();
		}
		
		log.info("sending a bad request...");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	/*
	 * 
	 */
	@PostMapping(value = "/add",
			produces = {"application/json","application/xml"},
			consumes = {"application/json","application/xml"})
	private ResponseEntity<StudentResponseDTO> addStudent(@RequestBody StudentRequestDTO student){
		log.info("StudentController::addStudent...");
		
		try {
			StudentResponseDTO addedStudent = this.service.saveStudent(student);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(addedStudent);
			
		}catch(Exception e) {
			log.error("ERROR:: error while adding student..." + e);
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	/*
	 * 
	 */
	@DeleteMapping("/delete/{studentId}")
	private ResponseEntity<StudentResponseDTO> deleteStudent(@PathVariable String studentId){
		log.info("StudentController::deleteStudent:: deleting student with id:: " + studentId);
		
		try {
			StudentResponseDTO deletedStudent = this.service.deleteStudent(studentId);
			
			return ResponseEntity.status(HttpStatus.OK).body(deletedStudent);
		}catch(Exception e) {
			log.error("ERROR:: error in deleting the student..");
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	/*
	 * 
	 */
	//Custom Query methods
	@GetMapping(value = "/byname/{studentName}", produces = {"application/json","application/xml"})
	public  ResponseEntity<List<StudentResponseDTO>> findStudentsByName(@PathVariable String studentName){
		log.info("StudentController::findStudentByName:: getting students with name : " + studentName);
		
		try {
			
			List<StudentResponseDTO> resultStudents = this.service.findByStudentName(studentName);
			
			return ResponseEntity.status(HttpStatus.OK).body(resultStudents);
			
		}catch(Exception e) {
			log.error("ERROR:: error in getting the student list with given name " + e);
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	/*
	 * 
	 */
	//JPQL methods
	@GetMapping(value = "/bymarks/{marks}", produces = {"application/json","application/xml"})
	public ResponseEntity<List<StudentResponseDTO>> findStudentsWithSameMarks(@PathVariable double marks){
		log.info("StudentController::findStudentsWithSameMarks:: getting list of students having marks as " + marks);
		
		try {
			
			List<StudentResponseDTO> resultStudents = this.service.findStudentsWithSameMarks(marks);
			
			return ResponseEntity.status(HttpStatus.OK).body(resultStudents);
			
		}catch(Exception e) {
			log.error("ERROR:: error in fetching records");
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
	}
	
	/*
	 * 
	 */
	@PostMapping(value = "/update",
			produces = {"application/json","application/xml"},
			consumes = {"application/json","application/xml"})
	public ResponseEntity<StudentResponseDTO> updateStudent (@RequestBody StudentUpdateRequestDTO studentReq){
		log.info("StudentController::updateStudent:");
		
		try {
			
			StudentResponseDTO updatedStudent = this.service.updateStudent(studentReq);
			
			return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
			
		}catch(Exception e) {
			log.error("ERROR:: error in updating the student record" + e);
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
