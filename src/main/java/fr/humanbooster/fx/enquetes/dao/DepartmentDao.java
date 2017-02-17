package fr.humanbooster.fx.enquetes.dao;

import java.util.Set;

import org.hibernate.Session;

import fr.humanbooster.fx.enquetes.business.Department;

public interface DepartmentDao {

	Department createDepartment(Department department);

	Department updateDepartment(Department department);

	boolean deleteDepartment(int idDepartment);

	Department findById(int idDepartment);

	Set<Department> findAll();

	Session openCurrentSession();

	Session openCurrentSessionWithTransaction();

	void closeCurrentSession();

	void closeCurrentSessionwithTransaction();

}
