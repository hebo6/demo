package com.example.demo.jpa;

import com.example.demo.jpa.entity.User;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface UserRepository extends JpaRepositoryImplementation<User, Long> {
}
