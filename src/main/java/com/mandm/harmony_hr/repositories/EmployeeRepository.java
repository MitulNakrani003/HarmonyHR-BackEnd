package com.mandm.harmony_hr.repositories;

import com.mandm.harmony_hr.dto.EmployeeEmailDto;
import com.mandm.harmony_hr.entities.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.address WHERE e.empId = :empId")
    Optional<Employee> findByEmpId(Integer empId);

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.address")
    List<Employee> findAll();

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.address WHERE e.email = :email")
    Optional<Employee> findByEmail(String email);

    @Query("SELECT new com.mandm.harmony_hr.dto.EmployeeEmailDto(e.empId, e.firstName, e.lastName, e.email) FROM Employee e")
    List<EmployeeEmailDto> findAllHiringManagerEmployeeEmails();

    @Query("SELECT u.belongsTo FROM Users u WHERE u.userId = :userId")
    Optional<Employee> findEmployeeByUserId(@Param("userId") Integer userId);
}
