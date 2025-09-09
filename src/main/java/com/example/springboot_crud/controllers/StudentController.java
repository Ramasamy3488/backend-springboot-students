package com.example.springboot_crud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot_crud.entities.Student;
import com.example.springboot_crud.exception.ResourceNotFoundException;
import com.example.springboot_crud.repositories.StudentRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/students")

public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@PostMapping
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable int id) {
		Student Student = studentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee with Employee Id " + id + " does not exist"));
		return ResponseEntity.ok(Student);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student studentDetails) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student with Student Id " + id + " does not exist"));
		student.setName(studentDetails.getName());
		student.setEmail(studentDetails.getEmail());
		student.setAge(studentDetails.getAge());
		student.setCity(studentDetails.getCity());
		studentRepository.save(student);
		return ResponseEntity.ok(student);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable int id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student with Student Id " + id + " does not exist"));
		studentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
