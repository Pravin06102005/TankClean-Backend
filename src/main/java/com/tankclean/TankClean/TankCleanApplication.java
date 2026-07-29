package com.tankclean.TankClean;

import com.tankclean.TankClean.entity.Users;
import com.tankclean.TankClean.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class  TankCleanApplication {

	public static void main(String[] args) {
		SpringApplication.run(TankCleanApplication.class, args);
	}

	@Bean
	CommandLineRunner createDefaultAdmin(
			UserRepository userRepository,
			PasswordEncoder passwordEncoder,
			@Value("${app.default-admin.name}") String adminName,
			@Value("${app.default-admin.email}") String adminEmail,
			@Value("${app.default-admin.phone}") String adminPhone,
			@Value("${app.default-admin.password}") String adminPassword
	) {
		return args -> {
			if (!userRepository.existsByRole("ADMIN")) {
				Users admin = new Users();
				admin.setName("Admin");
				admin.setEmail("admin@123.com");
				admin.setPhone("9096651403");
				admin.setPassword(passwordEncoder.encode("Admin@123"));
				admin.setRole("ADMIN");
				admin.setCreatedAt(LocalDateTime.now());
				userRepository.save(admin);
			}
		};
	}

}
