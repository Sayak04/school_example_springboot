package com.school.repository;

import com.school.models.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u WHERE CONCAT(u.firstName, ' ', u.lastName) LIKE %:name%")
    List<User> getUsersByName(@Param("name") String name);

    @Query("SELECT DISTINCT u FROM User u JOIN u.roles r WHERE r.title LIKE %:role%")
    List<User> getUsersByRole(@Param("role") String role);
}
