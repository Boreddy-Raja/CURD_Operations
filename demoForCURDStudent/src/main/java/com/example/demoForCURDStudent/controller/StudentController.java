package com.example.demoForCURDStudent.controller;

import java.util.List;

import java.util.*;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoForCURDStudent.entity.Student;
import com.example.demoForCURDStudent.repo.StudentRepo;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepo studentRepo;
	
	@PostMapping("/api/student")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		
		return new ResponseEntity<>(studentRepo.save(student),HttpStatus.CREATED);
	}
	
	@GetMapping("/api/allStudents")
	public ResponseEntity<List<Student>> getStudent() {
		List<Student> student = studentRepo.findAll();
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@GetMapping("api/student/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Long id) {
		java.util.Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			return new ResponseEntity<>(student.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/api/student/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentin) {
		System.out.println(studentin);
		java.util.Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			if(studentin.getStudentAddress()!=null) {
				student.get().setStudentAddress(studentin.getStudentAddress());
			}
			else {
				student.get().setStudentAddress(student.get().getStudentAddress());
			}
			if(studentin.getStudentName()!=null) {
				student.get().setStudentName(studentin.getStudentName());
			}
			else {
				student.get().setStudentName(student.get().getStudentName());
			}
			if(studentin.getStudentMail()!=null) {
				student.get().setStudentMail(studentin.getStudentMail());
			}
			else {
				student.get().setStudentMail(student.get().getStudentMail());
			}
			return new ResponseEntity<>(studentRepo.save(student.get()),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/api/student/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		
		java.util.Optional<Student> student = studentRepo.findById(id);
		
		if(student.isPresent()) {
			studentRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
