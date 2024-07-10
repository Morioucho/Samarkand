package com.morioucho.samarkand.repository;

import com.morioucho.samarkand.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
