package com.example.demo.controller;


import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Iterable<Employee>> findAll(){
        Iterable<Employee> findAll= employeeService.findAll();
        return new ResponseEntity<>(findAll,HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Iterable<Employee>> searchName(@RequestParam String name){
        Iterable<Employee> employees = employeeService.searchName("%"+name+"%");
        return new ResponseEntity<>(employees , HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id,@RequestBody Employee employee){
        Optional<Employee> employeeOptional = employeeService.findOne(id);
        if (!employeeOptional.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            employeeService.findOne(employeeOptional.get().getId());
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id){
        Optional<Employee> employeeOptional = employeeService.findOne(id);
        if (!employeeOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeService.delete(id);
        return new ResponseEntity<>(employeeOptional.get(),HttpStatus.NO_CONTENT);
    }
}
