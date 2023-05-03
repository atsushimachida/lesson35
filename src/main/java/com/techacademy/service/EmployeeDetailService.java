package com.techacademy.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.techacademy.entity.Authentication;
import com.techacademy.repository.AuthenticationRepository;

@Service
public class EmployeeDetailService implements UserDetailsService {
    private final AuthenticationRepository authenticationRepository;

    public EmployeeDetailService(AuthenticationRepository repository) {
        this.authenticationRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<Authentication> authentication = authenticationRepository.findById(name);
        if (!authentication.isPresent()) {
            throw new UsernameNotFoundException("Exception:Username Not Found");
        }
        return new EmployeeDetail(authentication.get().getEmployee());
    }
}