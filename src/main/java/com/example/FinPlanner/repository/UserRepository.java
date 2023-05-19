package com.example.FinPlanner.repository;

import com.example.FinPlanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);

    User findByFirstNameAndLastName(String firstName, String lastName);

    Optional<User> findUserByEmailAndPassword(String email, String password);
}
