package com.tankclean.TankClean.controller;


import com.tankclean.TankClean.dto.WorkerRequest;
import com.tankclean.TankClean.entity.Worker;
import com.tankclean.TankClean.service.WorkerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerService  workerService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Worker> createWorker(@ModelAttribute WorkerRequest dto) throws IOException {
        // Yahan hum dto aur uske andar se image nikal kar 2 arguments pass kar rahe hain
        return ResponseEntity.ok(workerService.addWorker(dto, dto.getImage()));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Worker> updateWorker(
            @PathVariable Long id,
            @ModelAttribute WorkerRequest dto) throws IOException {

        // Yahan hum id, dto aur image nikal kar 3 arguments pass kar rahe hain
        return ResponseEntity.ok(workerService.updateWorker(id, dto, dto.getImage()));
    }

    @GetMapping
    public List<Worker> getAllWorkers(){
        return workerService.getAllWorker();
    }

    @GetMapping("/{id}")
    public Worker getWorkerID(@PathVariable Long id){
        return workerService.getWorkerById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteWorker(@PathVariable Long id){
        workerService.deleteWorker(id);
    }

}
