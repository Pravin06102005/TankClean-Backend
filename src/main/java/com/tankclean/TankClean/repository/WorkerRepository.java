package com.tankclean.TankClean.repository;

import com.tankclean.TankClean.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    List<Worker> findByStatus(String status);
}
