/**
* Example comment.
*/

package com.example;

import java.util.UUID;
import java.util.List;
import javax.inject.Inject;

import org.apache.ibatis.annotations.Mapper;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeMapper employeeMapper;

    @Inject
    public EmployeeRepositoryImpl(final EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public int insert(final Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public int update(final Employee employee) {
        return employeeMapper.update(employee);
    }

    @Override
    public public int delete(final UUID employeeId) {
        return employeeMapper.delete(employee);
    }

    @Override
    public Employee findById(final UUID employeeId) {
        return employeeMapper.findById(employeeId);
    }

    @Override
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }
}