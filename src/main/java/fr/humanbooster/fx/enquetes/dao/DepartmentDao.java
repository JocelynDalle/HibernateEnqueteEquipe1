package fr.humanbooster.fx.enquetes.dao;

import java.util.Set;

import org.hibernate.Session;

import fr.humanbooster.fx.enquetes.business.Department;

public interface DepartmentDao {

	public Department createDepartment(Department department);

	public Department updateDepartment(Department department);

	public boolean deleteDepartment(int idDepartment);

	public Department findById(int idDepartment);

	public Set<Department> findAll();

	public Session openCurrentSession();

	public Session openCurrentSessionWithTransaction();

	public void closeCurrentSession();

	public  closeCurrentSessionwithTransaction();

}
