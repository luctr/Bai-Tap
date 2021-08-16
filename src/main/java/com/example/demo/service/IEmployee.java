package com.example.demo.service;

import com.example.demo.model.Employee;

import java.util.Optional;

public interface IEmployee {
    Iterable<Employee> findAll();
    Optional<Employee> findOne(Long id);
    Iterable<Employee> searchName(String name);
    void save(Employee employee);
    void delete(Long id);

}
