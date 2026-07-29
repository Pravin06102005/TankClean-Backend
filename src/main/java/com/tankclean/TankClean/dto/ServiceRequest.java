package com.tankclean.TankClean.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ServiceRequest {

    private MultipartFile image;
    @NotBlank
    private String serviceName;
    private String description;

    private Double price;
}
