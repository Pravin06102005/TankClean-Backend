package com.tankclean.TankClean.service;


import com.tankclean.TankClean.dto.ServiceRequest;
import com.tankclean.TankClean.entity.ServiceEntity;
import com.tankclean.TankClean.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceManagementService {

    @Autowired
    private ServiceRepository serviceRepository;


    public ServiceEntity addService(ServiceRequest request, org.springframework.web.multipart.MultipartFile file) throws java.io.IOException {
        ServiceEntity service = new ServiceEntity();
        service.setServiceName(request.getServiceName());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());

        if (file != null && !file.isEmpty()) {
            service.setImage(file.getBytes());
        }
        return serviceRepository.save(service);
    }

    public ServiceEntity updateService(Long id, ServiceRequest request, org.springframework.web.multipart.MultipartFile file) throws java.io.IOException {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        service.setServiceName(request.getServiceName());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());

        if (file != null && !file.isEmpty()) {
            service.setImage(file.getBytes());
        }
        return serviceRepository.save(service);
    }


    public List<ServiceEntity>getAllServices(){
        return serviceRepository.findAll();
    }

    public void deleteService(Long serviceId){
        serviceRepository.deleteById(serviceId);
    }
}
