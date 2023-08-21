package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Customer;

public class Mydao {
	private static final Object mobile = null;
	EntityManagerFactory e = Persistence.createEntityManagerFactory("dev");
	EntityManager m = e.createEntityManager();
	EntityTransaction t = m.getTransaction();

	public void save(Customer c) {
		t.begin();
		m.persist(c);
		t.commit();
	}

	public Customer fetchByEmail(String email) {
  List<Customer>list=m.createQuery("select x from Customer x where email=?1").setParameter(1, email).getResultList();
  if(list.isEmpty())
	  return null;
  else
	  return list.get(0);
	}

	public Customer fetchByMobile(long mobile_num) {
		List<Customer>list=m.createQuery("select x from Customer x where mobileno=?1").setParameter(1, mobile).getResultList();
		  if(list.isEmpty())
			  return null;
		  else
			  return list.get(0);
			}
	}


