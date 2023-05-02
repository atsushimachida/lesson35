package com.techacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;

public interface AuthenticationRepository extends JpaRepository<Authentication,String>{

}
