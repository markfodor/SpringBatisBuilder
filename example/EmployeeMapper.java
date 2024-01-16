/**
* Example comment.
*/

package com.example;

import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    int insert(Employee employee);
    int update(Employee employee);
    int delete(@Param("employeeId") UUID employeeId);
    Employee findById(@Param("employeeId") UUID employeeId);
    List<Employee> findAll();
}