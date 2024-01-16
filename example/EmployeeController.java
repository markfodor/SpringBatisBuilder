/**
* Example comment.
*/

package com.example;

import java.util.UUID;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EmployeeController {

    private final EmployeeService employeeService;

    @Inject
    public Employee(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // TODO check return type
    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ResponseEntity<Void> insertEmployee(@RequestBody Employee employee) {
        final int affectedRows = employeeService.insertEmployee(employee);
        // TODO handle error based on affectedRows
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // TODO check return type
    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ResponseEntity<Void> updateEmployee(final Employee employee) {
        final int affectedRows = employeeService.updateEmployee(employee);
        // TODO handle error based on affectedRows
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // TODO check return type and HTTP method for mapping
    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ResponseEntity<Void> deleteEmployeeById(final UUID employeeId) {
        final int affectedRows =  employeeService.deleteEmployee(employeeId);
        // TODO handle error based on affectedRows
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("employeeId") final UUID employeeId) {
        final Employee employee = employeeService.findEmployeeById(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @PreAuthorize("hasAuthority('SCOPE_urn:something:whatever')")
    public ResponseEntity<List<Employee>> findAll() {
        final List<Employee> employeeList = employeeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(employeeList);
    }
}