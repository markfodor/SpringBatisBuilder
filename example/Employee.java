/**
* Example comment.
*/

package com.example;

import java.util.UUID;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
class Employee {
    private UUID employeeId;
    private UUID companyId;
    private String firstName;
    private String lastName;
    private String department;
    private Date hireDate;
}