package com.inn.project1.project1.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="student")

@javax.persistence.NamedQueries({
	@NamedQuery(name="Student.getStudentGradesByGroup",query="select s.grades,count(*) from Student s group by s.grades")
})

@NamedNativeQueries({
	@NamedNativeQuery(name="Student.maxStudentIdentifier",query="select max(student_code)+1 from student_unique_code_generator where gender_code=:genderCode and result_code=:resultCode"),
	@NamedNativeQuery(name="Student.insertStudentIdentifier",query="insert into student_unique_code_generator(gender_code,result_code,student_code) value(:genderCode,:resultCode,1)"),
	@NamedNativeQuery(name="Student.updateStudentIdentifier",query="update student_unique_code_generator set student_code=:studentCode where gender_code=:genderCode and result_code=:resultCode")
})

public class Student implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	private String standard;
	
	@Column(name="student_identifier")
	private String studentIdentifier;
	
	@Column(name="roll_no")
	private Integer rollNo;
	
	public static enum Gender{
		Male,
		Female
	}
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@OneToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="studnet_mark_id")
	private Marks marks;
	
	public static enum Result{
		Pass,
		Fail
	}
	
	@Enumerated(EnumType.STRING)
	private Result result;
	
	public static enum Grades{
		A_PLUS,
		A,
		B_PLUS,
		B,
		C_PLUS,
		C,
		D;
	}
	
	@Enumerated(EnumType.STRING)
	private Grades grades;
	
	public Grades getGrades() {
		return grades;
	}

	public void setGrades(Grades grades) {
		this.grades = grades;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Marks getMarks() {
		return marks;
	}

	public void setMarks(Marks marks) {
		this.marks = marks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Integer getRollNo() {
		return rollNo;
	}

	public void setRollNo(Integer rollNo) {
		this.rollNo = rollNo;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String getStudentIdentifier() {
		return studentIdentifier;
	}

	public void setStudentIdentifier(String studentIdentifier) {
		this.studentIdentifier = studentIdentifier;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", standard=" + standard + ", rollNo=" + rollNo + ", gender="
				+ gender + "]";
	}
	
	
}
