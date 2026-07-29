package com.tankclean.TankClean.repository;

import com.tankclean.TankClean.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity,Long> {
}
