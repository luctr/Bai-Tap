package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    @Query("select e from Employee e where e.name like ?1")
    Iterable<Employee> findByName(String name);
}
