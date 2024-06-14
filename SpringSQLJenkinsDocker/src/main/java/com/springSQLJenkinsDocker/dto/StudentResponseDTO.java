package com.springSQLJenkinsDocker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StudentResponseDTO {

	private String studentId;
	
	private String studentName;
	
	private double studentMarks;
}
