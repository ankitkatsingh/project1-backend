package com.inn.project1.project1.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.inn.project1.project1.dao.IStudentDao;
import com.inn.project1.project1.models.Student;

public class StudentDaoImpl implements IStudentDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public <S extends Student> S save(S entity) {
		return this.save(entity);
	}

	@Override
	public <S extends Student> Iterable<S> saveAll(Iterable<S> entities) {
		return this.saveAll(entities);
	}

	@Override
	public Optional<Student> findById(Integer id) {
		return this.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return this.existsById(id);
	}

	@Override
	public Iterable<Student> findAll() {
		return this.findAll();
	}

	@Override
	public Iterable<Student> findAllById(Iterable<Integer> ids) {
		return this.findAllById(ids);
	}

	@Override
	public long count() {
		return this.count();
	}

	@Override
	public void deleteById(Integer id) {
		this.deleteById(id);
	}

	@Override
	public void delete(Student entity) {
		this.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends Student> entities) {
		this.deleteAll();
	}

	@Override
	public void deleteAll() {
		this.deleteAll();
	}

	@Override
	public List<Object[]> getStudentGradesByGroup() {
		Query query = this.entityManager.createNamedQuery("Student.getStudentGradesByGroup");
		return (List<Object[]>)query.getResultList();
	}

	@Override
	public BigInteger maxStudentIdentifier(String genderCode, String resultCode) {
		Query query = this.entityManager.createNativeQuery("Student.maxStudentIdentifier").setParameter("genderCode", genderCode).setParameter("resultCode", resultCode);
		return (BigInteger)query.getSingleResult();
	}

	
	@Override
	public Integer insertStudentIdentifier(String genderCode, String resultCode) {
		Query query = this.entityManager.createNativeQuery("Student.insertStudentIdentifier").setParameter("genderCode", genderCode).setParameter("resultCode", resultCode);
		return (Integer)query.executeUpdate();
	}

	@Override
	public Integer updateStudentIdentifier(String studentCode, String genderCode, String resultCode) {
		return null;
	}
	
}
