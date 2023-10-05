package com.psr.dynamodblearning.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.psr.dynamodblearning.entity.Employee;
import com.psr.dynamodblearning.repo.EmployeeRepo;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final DynamoDBMapper dynamoDBMapper;

    public EmployeeService(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Employee saveEmployee(Employee employee) {
        dynamoDBMapper.save(employee);
        return employee;
    }

    public Employee getEmployeeById(String employeeId) {
        return dynamoDBMapper.load(Employee.class, employeeId);
    }

    public String deleteEmployeeById(String employeeId) {
        dynamoDBMapper.delete(dynamoDBMapper.load(Employee.class, employeeId));
        return "Employee Id : " + employeeId + " deleted";
    }

    public Employee updateEmployee(String employeeId, Employee employee) {
        dynamoDBMapper.save(employee,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("employeeId", new ExpectedAttributeValue(
                                new AttributeValue().withS(employeeId)
                        )));
        return employee;
    }
}
