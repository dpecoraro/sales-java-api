package org.udemy.spring.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.udemy.spring.course.domain.bo.User;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);
}
