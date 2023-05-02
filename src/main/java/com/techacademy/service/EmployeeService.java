package com.techacademy.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.repository.AuthenticationRepository;
import com.techacademy.repository.EmployeeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    /** 全件を検索して返す */
    public List<Employee> getEmployeeList(){
        return employeeRepository.findAll();
    }

    /** Employee登録処理　  */
    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /** Employeeを1件検索して返す　*/
    public Employee getEmployee(Integer id) {
        return employeeRepository.findById(id).get();
    }

    /** Employeeの削除を行う */
    @Transactional
    public void deleteEmployee(Integer id) {
        Employee currentEmployee = this.getEmployee(id);
        currentEmployee.setDelete_flag(1);
        employeeRepository.save(currentEmployee);
    }

}
