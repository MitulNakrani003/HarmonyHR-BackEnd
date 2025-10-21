package com.mandm.harmony_hr.services;

import com.mandm.harmony_hr.dto.EmployeeEmailDto;
import com.mandm.harmony_hr.entities.Employee;
import com.mandm.harmony_hr.entities.Users;
import com.mandm.harmony_hr.repositories.EmployeeRepository;
import com.mandm.harmony_hr.repositories.UsersRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UsersRepository usersRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                            UsersRepository usersRepository) {
        this.employeeRepository = employeeRepository;
        this.usersRepository = usersRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int empId) {
        return employeeRepository.findByEmpId(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + empId));
    }

    public Employee getEmployeeByUserId(int userId) {
        Users user = usersRepository.getByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found with user id: " + userId));
        return user.getBelongsTo();
    }

    public List<EmployeeEmailDto> getAllHiringManagerEmployeeEmails() {
        return employeeRepository.findAllHiringManagerEmployeeEmails();
    }
}
