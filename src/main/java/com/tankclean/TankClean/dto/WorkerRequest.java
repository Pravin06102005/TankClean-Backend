package com.tankclean.TankClean.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class WorkerRequest {

    @NotBlank
    private String name;

    @Pattern(regexp = "^[0-9]{10}$")
    private String phone;
    private String status;
    private MultipartFile image;
}
