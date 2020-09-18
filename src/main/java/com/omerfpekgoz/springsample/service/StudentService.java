package com.omerfpekgoz.springsample.service;

import java.util.List;

import com.omerfpekgoz.springsample.model.Student;

public interface StudentService {

	List<Student> getAllStudent();

	Student findById(Long studentId);

	Student addStudent(Student student);

	Student updateStudent(Student student);

	void deleteStudent(Long studentId);

}
