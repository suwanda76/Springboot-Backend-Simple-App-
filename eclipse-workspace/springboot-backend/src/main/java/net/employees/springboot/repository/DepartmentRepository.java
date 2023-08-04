package net.employees.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import net.employees.springboot.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
}
