package edu.usm.healthsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Client {

    private String username;
    private String name;
    private String lastName;
    private String passwordHash;
    private EmployeeType employeeType;
    private String employeeId;

    @Override
    public String getUsername() { return username; }
    @Override
    public String getName() { return name; }
    @Override
    public String getLastName() { return lastName; }
    @Override
    public String getPassword() { return passwordHash; }

}