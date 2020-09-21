package com.omerfpekgoz.springsample.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyLong;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.omerfpekgoz.springsample.model.Student;
import com.omerfpekgoz.springsample.repositories.StudentRepository;

import jdk.jshell.spi.ExecutionControl.UserException;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

	@InjectMocks
	private StudentServiceImpl studentService;

	@Mock
	private StudentRepository studentRepository;

	@Test
	void testGetAllStudent() {
		List<Student> list = new ArrayList<Student>();

		list.add(new Student(1L, "Test-Name1", "Test-Surname1"));
		list.add(new Student(2L, "Test-Name2", "Test-Surname2"));
		list.add(new Student(3L, "Test-Name3", "Test-Surname3"));

		when(studentRepository.findAll()).thenReturn(list);

		List<Student> studentsList = studentService.getAllStudent();
		assertEquals(3, studentsList.size());

		verify(studentRepository, times(1)).findAll();
	}

	@Test
	void testFindById() {

		Optional<Student> student = Optional.ofNullable(new Student(1L, "Test-Name1", "Test-Surname1"));
		when(studentRepository.findById(1L)).thenReturn(student);

		Student st = studentService.findById(1L);

		assertEquals("Test-Name1", st.getName());
		assertEquals("Test-Surname1", st.getSurname());

	}

	@Test
	void testAddStudent() {

		Student student = new Student(1L, "Test-Name1", "Test-Surname1");
		studentService.addStudent(student);
		verify(studentRepository, times(1)).save(student);
	}

	@Test
	void testUpdateStudent() {
		Student student = new Student(1L, "Test-Name1", "Test-Surname1");
		studentRepository.save(student);
		studentRepository.flush();

		Student updateStudent = new Student();
		updateStudent.setName("Test-Name2");
		updateStudent.setId(1L);

		given(studentRepository.findById(student.getId())).willReturn(Optional.of(student));
		studentService.updateStudent(updateStudent);
		verify(studentRepository).findById(updateStudent.getId());

	}

	@Test
	public void should_throw_exception_when_updated_student_doesnt_exist() {
		Student student = new Student(1L, "Test-Name1", "Test-Surname1");
		studentRepository.save(student);
		studentRepository.flush();

		Student updateStudent = new Student();
		updateStudent.setName("Test-Name2");
		updateStudent.setId(1L);

		given(studentRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
		studentService.updateStudent(updateStudent);
	}

	@Test
	void testDeleteStudent() {
		Student student = new Student(1L, "Test-Name", "Test-Surname");
		studentRepository.save(student);
		studentRepository.flush();

		studentService.deleteStudent(student.getId());
		studentRepository.flush();
		assertEquals(studentRepository.findById(student.getId()), Optional.empty());

	}

	@Test
	public void should_throw_exception_when_deleted_student_doesnt_exist() {

		Student student = new Student(1L, "Test-Name", "Test-Surname");
		studentRepository.save(student);
		studentRepository.flush();
		
		studentService.deleteStudent(student.getId());
		studentRepository.flush();

		when(studentRepository.findById(student.getId())).thenThrow(NullPointerException.class);

		assertThrows(NullPointerException.class, () -> {
			studentService.deleteStudent(student.getId());
		});
		
	}
}
