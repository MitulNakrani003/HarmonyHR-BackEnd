package com.mandm.harmony_hr;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mandm.harmony_hr.entities.Employee;
import com.mandm.harmony_hr.services.EmployeeService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final EmployeeService employeeService;

    public ProfileController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employeeInfo/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<Employee> getEmployeeByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(employeeService.getEmployeeByUserId(userId));
    }
}
