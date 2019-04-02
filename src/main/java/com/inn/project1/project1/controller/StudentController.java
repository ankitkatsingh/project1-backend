package com.inn.project1.project1.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inn.project1.project1.models.Student;
import com.inn.project1.project1.service.StudentService;
import com.inn.project1.project1.utils.StudentWrapper;

@RestController
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/api/getAllStudent")
	public List<Student> getAllStudent(){
		try {
			return (List<Student>) studentService.getAllStudents();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/api/createNewStudent")
	public Student createNewStudent(@RequestBody Student student) {
		try {
			return studentService.createNewStudent(student);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping("/api/deleteById/{id}")
	public void deleteById(@PathVariable("id") Integer id) {
		try {
			studentService.deleteStudentById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/api/findById/{id}")
	public Optional<Student> findById(@PathVariable("id") Integer id) {
		try {
			return studentService.findById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PutMapping("/api/updateStudent")
	public Student updateStudent(@RequestBody Student student) {
		try {
			return studentService.createNewStudent(student);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/api/exportStudnetList")
	public String exportStudnetList() throws IOException, InvalidFormatException {
		String destinationFilePath =  studentService.exportStudent();
		return "{\"destinationFilePath\":\""+destinationFilePath+"\"}";
	}
	
	@PostMapping("/api/createStudnetWithMarks")
	public StudentWrapper createStudnetWithMarks(@RequestBody StudentWrapper studentWrapper) {
		try {
			return studentService.createStudnetWithMarks(studentWrapper);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/api/getStudentGradesByGroup")
	public List<Object[]> getStudentGradesByGroup(){
		try {
			return studentService.getStudentGradesByGroup();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
