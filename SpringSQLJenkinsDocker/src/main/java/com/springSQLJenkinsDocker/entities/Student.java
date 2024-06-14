package com.springSQLJenkinsDocker.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //JPA converts the object to database row
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="student_table")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "student_id")
	private String studentId;
	
	@Column(name = "student_name")
	private String studentName;
	
	@Column(name = "student_marks")
	private double studentMarks;
}
