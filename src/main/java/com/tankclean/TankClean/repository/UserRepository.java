package com.tankclean.TankClean.repository;

import com.tankclean.TankClean.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);//NullPointerException without optional
    boolean existsByRole(String role);
}
