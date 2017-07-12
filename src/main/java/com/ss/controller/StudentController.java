package com.ss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.data.StudentRepository;
import com.ss.model.Student;

@RestController
@RequestMapping("/studs")
public class StudentController {

	
	@Autowired
	private StudentRepository sturepo;
	
		
	@RequestMapping(value="/public/stu", method=RequestMethod.POST)
	public ResponseEntity<Student> addStudent(@RequestBody Student student){		
		sturepo.save(student);		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/public/stu", method=RequestMethod.GET)
	public ResponseEntity<List<Student>> getStudents(){		
		List<Student> stu = sturepo.findAll();		
		return new ResponseEntity<>(stu, HttpStatus.OK);
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ResponseEntity<String> getHome(){		
	
		return new ResponseEntity<>("Home page", HttpStatus.OK);
	}
	
	
}
