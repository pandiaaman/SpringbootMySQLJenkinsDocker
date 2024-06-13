package com.springSQLJenkinsDocker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springSQLJenkinsDocker.entities.Student;

public interface StudentRepository extends JpaRepository<Student, String> {

}
