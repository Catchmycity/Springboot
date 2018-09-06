package com.demo.spring;

import com.demo.spring.entity.Emp;
import java.util.List;

import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmpService {

    @Autowired
    private SessionFactory sessionFactory;

    public String save(Emp e) {
        Session s = sessionFactory.getCurrentSession();
        s.save(e);
        return "success";
    }

    public int delete(int empId) {
        Session s = sessionFactory.getCurrentSession();
        int deletedCount = s.createQuery("delete from Emp where empId=:empId").setParameter("empId", empId).executeUpdate();
        return deletedCount;
    }

    public Emp findById(int empId) {
        Session s = sessionFactory.getCurrentSession();
        Emp e = s.find(Emp.class, empId);
        return e;
    }

    public List<Emp> getEmpList() {
        Session s = sessionFactory.getCurrentSession();
        Query query = s.createQuery("select e from Emp e");
        return query.getResultList();

    }
}
