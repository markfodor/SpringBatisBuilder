/**
* Example comment.
*/

package com.example;

import java.util.UUID;
import java.util.List;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepositoryImpl employeeRepository;

    @Inject
    public EmployeeService(final EmployeeRepositoryImpl employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public int insertEmployee(final Employee employee) {
        return employeeRepository.insert(employee);
    }

    public int updateEmployee(final Employee employee) {
        return employeeRepository.update(employee);
    }

    public int deleteEmployeeById(final UUID employeeId) {
        return employeeRepository.delete(employeeId);
    }

    public Employee findEmployeeById(final UUID employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}