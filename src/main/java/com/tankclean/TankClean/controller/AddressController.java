package com.tankclean.TankClean.controller;

import com.tankclean.TankClean.dto.AddressRequest;
import com.tankclean.TankClean.entity.Address;
import com.tankclean.TankClean.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @PostMapping
    public Address addAddress(@Valid @RequestBody AddressRequest addressRequest){
        return addressService.addAddress(addressRequest);
    }

    @GetMapping("/my")
    public List<Address> getMyAddresses() {
        return addressService.getMyAddresses();
    }

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/user/{userId}")
    public List<Address> getAddressesByUserId(@PathVariable Long userId) {
        return addressService.getAddressesByUserId(userId);
    }

    @PutMapping
    public Address updateAddress(@Valid @RequestBody AddressRequest addressRequest){
        return addressService.updateAddress(addressRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
    }
}
