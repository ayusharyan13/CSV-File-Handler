package com.example.assignment.EventStrat.repository;
import com.example.assignment.EventStrat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}