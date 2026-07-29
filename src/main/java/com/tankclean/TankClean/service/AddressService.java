package com.tankclean.TankClean.service;

import com.tankclean.TankClean.dto.AddressRequest;
import com.tankclean.TankClean.entity.Address;
import com.tankclean.TankClean.entity.Users;
import com.tankclean.TankClean.entity.Worker;
import com.tankclean.TankClean.repository.AddressRepository;
import com.tankclean.TankClean.repository.UserRepository;
import com.tankclean.TankClean.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public Address addAddress(AddressRequest addressRequest) {
        String email = SecurityUtil.getCurrentUserEmail();
        Users users = userRepository.findByEmail(email).orElseThrow();
        Address address=new Address();
        address.setBuilding(addressRequest.getBuilding());
        address.setArea(addressRequest.getArea());
        address.setCity(addressRequest.getCity());
        address.setPincode(addressRequest.getPincode());
        address.setUser(users);

        return addressRepository.save(address);
    }

    public List<Address> getMyAddresses() {

        String email = SecurityUtil.getCurrentUserEmail();
        Users user = userRepository.findByEmail(email).orElseThrow();

        return addressRepository.findByUser_UserId(user.getUserId());
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public List<Address> getAddressesByUserId(Long userId) {
        return addressRepository.findByUser_UserId(userId);
    }

    public Address updateAddress(AddressRequest addressRequest) {
        // 1. Find the specific address by its Unique ID
        Address address = addressRepository.findById(addressRequest.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressRequest.getAddressId()));

        // 2. Update the fields from the request
        address.setBuilding(addressRequest.getBuilding());
        address.setArea(addressRequest.getArea());
        address.setCity(addressRequest.getCity());
        address.setPincode(addressRequest.getPincode());
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {

        String email = SecurityUtil.getCurrentUserEmail();
        Users user = userRepository.findByEmail(email).orElseThrow();

        Address address = addressRepository.findById(id).orElseThrow();

        if (!SecurityUtil.isAdmin() && !address.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        addressRepository.delete(address);
    }
}
