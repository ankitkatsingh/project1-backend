package com.inn.project1.project1.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inn.project1.project1.models.Student;

@Repository
public interface IStudentDao extends CrudRepository<Student,Integer>{
	
	public List<Object[]> getStudentGradesByGroup();
	
	public BigInteger maxStudentIdentifier(String genderCode,String resultCode);
	
	//public Integer insertStudentIdentifier(String genderCode,String resultCode);
	
	//public void updateStudentIdentifier(String studentCode,String genderCode,String resultCode);
	/*
	@Query(value="select s.grades,count(*) from Student s group by s.grades")
	public List<Object[]> getStudentGradesByGroup();
	
	@Query(value="select max(student_code)+1 from student_unique_code_generator where gender_code=:genderCode and result_code=:resultCode",nativeQuery=true)
	public BigInteger maxStudentIdentifier(@Param("genderCode") String genderCode,@Param("resultCode") String resultCode);
	*/

	@Modifying
	@Transactional
	@Query(value="insert into student_unique_code_generator(gender_code,result_code,student_code) value(:genderCode,:resultCode,1)",nativeQuery=true)
	public Integer insertStudentIdentifier(@Param("genderCode") String genderCode,@Param("resultCode") String resultCode);
	
	
	@Modifying
	@Transactional
	@Query(value="update student_unique_code_generator set student_code=:studentCode where gender_code=:genderCode and result_code=:resultCode",nativeQuery=true)
	public Integer updateStudentIdentifier(@Param("studentCode") String studentCode,@Param("genderCode") String genderCode,@Param("resultCode") String resultCode);
	
}
