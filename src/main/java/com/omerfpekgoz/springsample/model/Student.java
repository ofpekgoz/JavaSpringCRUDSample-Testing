package com.omerfpekgoz.springsample.model;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

	public Student() {

	}

	public Student(Long id, String name, String surname) {

		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
