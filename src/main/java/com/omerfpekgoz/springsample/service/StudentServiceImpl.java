package com.omerfpekgoz.springsample.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omerfpekgoz.springsample.model.Student;
import com.omerfpekgoz.springsample.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	@Override
	public Student findById(Long studentId) {
		Optional<Student> studentDb = studentRepository.findById(studentId);

		if (studentDb.isPresent()) {
			return studentDb.get();
		} else {
			return null;
		}

	}

	@Override
	public Student addStudent(Student student) {

		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Student student) {

		Optional<Student> studentDb = studentRepository.findById(student.getId());

		if (studentDb.isPresent()) {
			Student updateStudent = studentDb.get();
			updateStudent.setName(student.getName());
			updateStudent.setSurname(student.getSurname());

			studentRepository.save(updateStudent);
			return updateStudent;
		} else {
			return null;
		}

	}

	@Override
	public void deleteStudent(Long studentId) {

		Optional<Student> studentDb = studentRepository.findById(studentId);

		if (studentDb.isPresent()) {
			studentRepository.delete(studentDb.get());
		} else {
			new UserPrincipalNotFoundException("Kayıt Bulunamadı " + studentId);
		}
	}

}
