package io.javatricks.springboot.thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.javatricks.springboot.thymeleaf.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}