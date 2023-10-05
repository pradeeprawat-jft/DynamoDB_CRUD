package com.psr.dynamodblearning.controller;

import com.psr.dynamodblearning.entity.Employee;
import com.psr.dynamodblearning.repo.EmployeeRepo;
import com.psr.dynamodblearning.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("{employeeId}")
    public Employee getEmployee(@PathVariable String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @DeleteMapping("{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId) {
        return employeeService.deleteEmployeeById(employeeId);
    }

    @PutMapping("{employeeId}")
    public Employee updateEmployee(@PathVariable String employeeId, @RequestBody  Employee employee) {
        System.out.println(employee);
        return employeeService.updateEmployee(employeeId, employee);
    }
}
