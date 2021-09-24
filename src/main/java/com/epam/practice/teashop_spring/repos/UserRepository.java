package com.epam.practice.teashop_spring.repos;

import com.epam.practice.teashop_spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String code);
    User removeUserByUsername(String username);

}