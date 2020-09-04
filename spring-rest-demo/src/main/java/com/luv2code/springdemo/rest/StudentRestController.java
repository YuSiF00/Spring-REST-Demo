package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.ws.http.HTTPBinding;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;
import com.luv2code.springdemo.entity.StudentErrorResponse;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> students;

	@PostConstruct
	public void loadData() {
        students = new ArrayList<Student>();

		Student student1 = new Student("Yusif", "Memmedov");
		Student student2 = new Student("Cavidan", "Abdullayev");
		Student student3 = new Student("Kenan", "Sultanov");

		students.add(student1);
		students.add(student2);
		students.add(student3);

	}

	

	@GetMapping("/students")
	public List<Student> getStudents() {

		return students;

	}
	
	
	@GetMapping("/students/{studentId}")
	private Student getStudent(@PathVariable int studentId) {
		
		// check the studentId against list size
		if(studentId>=students.size() || studentId<0) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}
		
		return students.get(studentId);
	}
	
	
	
}
