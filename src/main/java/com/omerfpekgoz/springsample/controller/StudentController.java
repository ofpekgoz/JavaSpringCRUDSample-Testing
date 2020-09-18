package com.omerfpekgoz.springsample.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omerfpekgoz.springsample.model.Student;
import com.omerfpekgoz.springsample.service.StudentService;
import com.omerfpekgoz.springsample.service.StudentServiceImpl;

@RestController
@RequestMapping(value = "/student", produces = "application/json")
public class StudentController {

	@Autowired
	private StudentServiceImpl studentServiceImpl;

	@RequestMapping(value = "/list")
	public List<Student> gettAllStudent() {
		return studentServiceImpl.getAllStudent();
	}

	@RequestMapping(value = "/list/{id}")
	public Student findById(@PathVariable Long id) {
		return studentServiceImpl.findById(id);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Student addStudent(@RequestBody Student student) {
		return studentServiceImpl.addStudent(student);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
		return studentServiceImpl.updateStudent(student);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public boolean deleteDeveloper(@PathVariable Long id) {
		studentServiceImpl.deleteStudent(id);
		return true;
	}
}
