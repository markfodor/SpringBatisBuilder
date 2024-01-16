/**
* Example comment.
*/

package com.example;

import java.util.UUID;
import java.util.List;

public interface EmployeeRepository {
    int insert(Employee employee);
    int update(Employee employee);
    int delete(UUID employeeId);
    Employee findById(UUID employeeId);
    List<Employee> findAll();
}