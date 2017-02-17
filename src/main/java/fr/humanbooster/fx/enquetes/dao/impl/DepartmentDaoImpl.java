package fr.humanbooster.fx.enquetes.dao.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import fr.humanbooster.fx.enquetes.business.Department;
import fr.humanbooster.fx.enquetes.dao.DepartmentDao;

public class DepartmentDaoImpl implements DepartmentDao {

	private Session session;
	private Transaction transaction;

	// Create
	@Override
	public Department createDepartment(Department department) {
		session.save(department);
		return department;
	}

	// Update
	@Override
	public Department updateDepartment(Department department) {
		session.saveOrUpdate(department);
		return department;
	}

	// Delete
	@Override
	public boolean deleteDepartment(int idDepartment) {
		Department department = this.findById(idDepartment);
		if (department == null)
			return false;
		session.delete(department);
		return true;
	}

	@Override
	public Department findById(int idDepartment) {

		return session.byId(Department.class).load(idDepartment);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Department> findAll() {
		List<Department> lsDepartment = session.createQuery("from Department").getResultList();
		Set<Department> setDepartment = new TreeSet<Department>();
		setDepartment.addAll(lsDepartment);
		return setDepartment;

	}

	@Override

	public Session openCurrentSession() {
		session = getSessionFactory().openSession();
		return session;

	}

	@Override

	public Session openCurrentSessionWithTransaction() {
		session = getSessionFactory().openSession();
		transaction = session.beginTransaction();
		return session;
	}

	@Override

	public void closeCurrentSession() {
		session.close();
	}

	@Override

	public void closeCurrentSessionwithTransaction() {
		transaction.commit();
		session.close();

	}

	private SessionFactory getSessionFactory() {

		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml")
				.build();
		Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

		return sessionFactory;

	}

}

