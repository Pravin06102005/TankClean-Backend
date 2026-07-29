package com.tankclean.TankClean.service;

import com.tankclean.TankClean.dto.WorkerRequest;
import com.tankclean.TankClean.entity.Worker;
import com.tankclean.TankClean.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public Worker addWorker(WorkerRequest workerRequest, MultipartFile imageFile) throws IOException {
        Worker worker = new Worker();
        worker.setName(workerRequest.getName());
        worker.setPhone(workerRequest.getPhone());
        worker.setStatus(workerRequest.getStatus());

        if (imageFile != null && !imageFile.isEmpty()) {
            worker.setImage(imageFile.getBytes());
        }

        return workerRepository.save(worker);
    }




    public List<Worker> getAllWorker(){
        return workerRepository.findAll();
    }

    public Worker getWorkerById(Long workerId){
        return workerRepository.findById(workerId).get();
    }

    public Worker updateWorker(Long id, WorkerRequest workerRequest, MultipartFile imageFile) throws IOException {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        worker.setName(workerRequest.getName());
        worker.setPhone(workerRequest.getPhone());
        worker.setStatus(workerRequest.getStatus());

        if (imageFile != null && !imageFile.isEmpty()) {
            worker.setImage(imageFile.getBytes());
        }

        return workerRepository.save(worker);
    }

    public void deleteWorker(Long workerId){
        workerRepository.deleteById(workerId);
    }
}
