package com.tankclean.TankClean.repository;

import com.tankclean.TankClean.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
