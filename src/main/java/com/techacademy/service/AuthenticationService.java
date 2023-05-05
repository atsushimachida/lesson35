package com.techacademy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.repository.AuthenticationRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationService(AuthenticationRepository repository) {
        this.authenticationRepository = repository;
    }

    /** Employeeを検索して返す　*/
    public Optional<Authentication> getEmployeeCode(String code) {
        return authenticationRepository.findById(code);
    }


}
