package fr.humanbooster.fx.enquetes.dao.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.humanbooster.fx.enquetes.business.Department;
import fr.humanbooster.fx.enquetes.dao.DepartmentDao;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	@Autowired
	private SessionFactory sf;

	// Create
	@Override
	public Department createDepartment(Department department) {
		sf.getCurrentSession().save(department);
		return department;
	}

	// Update
	@Override
	public Department updateDepartment(Department department) {
		sf.getCurrentSession().saveOrUpdate(department);
		return department;
	}

	// Delete
	@Override
	public boolean deleteDepartment(int idDepartment) {
		Department department = this.findById(idDepartment);
		if (department == null)
			return false;
		sf.getCurrentSession().delete(department);
		return true;
	}

	@Override
	public Department findById(int idDepartment) {

		return sf.getCurrentSession().byId(Department.class).load(idDepartment);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Department> findAll() {
		List<Department> lsDepartment = sf.getCurrentSession().createQuery("from Department").getResultList();
		Set<Department> setDepartment = new TreeSet<Department>();
		setDepartment.addAll(lsDepartment);
		return setDepartment;

	}

}

