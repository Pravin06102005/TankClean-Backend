package com.tankclean.TankClean.controller;


import com.tankclean.TankClean.dto.ServiceRequest;
import com.tankclean.TankClean.entity.ServiceEntity;
import com.tankclean.TankClean.service.ServiceManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceManagementService service;

    @PostMapping(consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public ServiceEntity createService(@Valid @ModelAttribute ServiceRequest serviceRequest) throws java.io.IOException {
        return service.addService(serviceRequest, serviceRequest.getImage());
    }

    @PutMapping(value = "/{id}", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public ServiceEntity updateService(
            @PathVariable Long id,
            @Valid @ModelAttribute ServiceRequest serviceRequest) throws java.io.IOException {
        return service.updateService(id, serviceRequest, serviceRequest.getImage());
    }

    @GetMapping
    public List<ServiceEntity> getAllServices(){
        return service.getAllServices();
    }

    @DeleteMapping("/{id}")
    public void delteService(@PathVariable Long id){
        service.deleteService(id);
    }
}
