package com.demo.spring;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmpService {

	@PersistenceContext
	EntityManager emf;

	public String save(Emp e) {
		emf.persist(e);
		return "success";
	}

	public int delete(int empId) {

		int deletedCount = emf.createQuery("DELETE FROM Emp where empId=?").setParameter(0, empId).executeUpdate();
		return deletedCount;
	}

	public Emp findById(int empId) {
		Emp e = emf.find(Emp.class, empId);
		return e;
	}

	
	public List<Emp> getEmpList() {

		Query query = emf.createQuery("select e from Emp e");
		return query.getResultList();

	}
}
