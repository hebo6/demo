package com.example.demo.jpa;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface UserRepository extends JpaRepositoryImplementation<User, Long> {
}
