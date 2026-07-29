package com.tankclean.TankClean.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {

    private Long addressId;

    @NotNull
    private String city;
    @NotNull
    private String area;
    @NotNull
    private String building;

    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
    @Size(min = 6, max = 6,message = "Pincode must be 6 digits")
    private String pincode;

    private Long userId;
}
