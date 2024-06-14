package com.springSQLJenkinsDocker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springSQLJenkinsDocker.entities.Student;

public interface StudentRepository extends JpaRepository<Student, String> {

	//custom query method
	public List<Student> findByStudentName(String studentName);
	
	//JPQL(java persistence query language)
	@Query("SELECT s FROM Student s WHERE s.studentMarks = :marks")
	public List<Student> studentsWithSameMarks(@Param("marks") double marks);
}
