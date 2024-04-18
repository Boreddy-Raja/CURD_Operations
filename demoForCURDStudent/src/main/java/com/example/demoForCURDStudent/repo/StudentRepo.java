package com.example.demoForCURDStudent.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoForCURDStudent.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long>{

}
