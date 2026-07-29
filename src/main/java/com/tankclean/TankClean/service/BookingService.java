package com.tankclean.TankClean.service;

import com.tankclean.TankClean.dto.BookingRequest;
import com.tankclean.TankClean.entity.*;
import com.tankclean.TankClean.repository.*;
import com.tankclean.TankClean.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
   private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private WorkerRepository workerRepository;


    public Booking createBooking(BookingRequest bookingRequest){

        String email = SecurityUtil.getCurrentUserEmail();
        Users users = userRepository.findByEmail(email).orElseThrow();
        Address address=addressRepository.findById(bookingRequest.getAddressId()).orElseThrow();

        // 🔐 SECURITY CHECK (VERY IMPORTANT)
        if (!address.getUser().getUserId().equals(users.getUserId())) {
            throw new RuntimeException("You cannot use another user's address");
        }

        ServiceEntity service=serviceRepository.findById(bookingRequest.getServiceId()).orElseThrow();

        Booking booking = new Booking();
        booking.setUser(users);
        booking.setAddress(address);
        booking.setServices(service);
//        booking.setWorker(worker);
        booking.setBookingDate(LocalDate.now());
        booking.setServiceDate(bookingRequest.getServiceDate());
        booking.setStatus(BookingStatus.PENDING);
        booking.setTotalPrice(service.getPrice());

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    public List<Booking> getMyBookings(){
        String email = SecurityUtil.getCurrentUserEmail();
        Users user = userRepository.findByEmail(email).orElseThrow();

        return bookingRepository.findByUserUserId(user.getUserId());
    }

    // Naya method jo Status aur Worker dono ko ek saath update karega
    public Booking updateBookingAdmin(Long id, String status, Long workerId) {
        // 1. Booking dhundo
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // 2. Status update karein (Agar status bheja gaya hai)
        if (status != null) {
            booking.setStatus(BookingStatus.valueOf(status));
        }

        // 3. Worker assign karein
        if (workerId != null) {
            Worker worker = workerRepository.findById(workerId)
                    .orElseThrow(() -> new RuntimeException("Worker not found"));
            booking.setWorker(worker);

            // Agar status PENDING hai aur worker assign ho raha hai, toh auto ASSIGNED kar dein
            if (booking.getStatus() == BookingStatus.PENDING) {
                booking.setStatus(BookingStatus.ASSIGNED);
            }
        } else {
            // Agar workerId null bheja hai, toh worker hata dein
            booking.setWorker(null);
        }

        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id){
        String email = SecurityUtil.getCurrentUserEmail();
        Users currentUser = userRepository.findByEmail(email).orElseThrow();
        Booking booking = bookingRepository.findById(id).orElseThrow();

        boolean isOwner = booking.getUser().getUserId().equals(currentUser.getUserId());
        if (!SecurityUtil.isAdmin() && !isOwner) {
            throw new RuntimeException("Unauthorized");
        }

        bookingRepository.delete(booking);
    }


    public Booking assignWorker(Long bookingId, Long workerId){

        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        Worker worker = workerRepository.findById(workerId).orElseThrow();

        booking.setWorker(worker);
        booking.setStatus(BookingStatus.ASSIGNED);

        return bookingRepository.save(booking);
    }
}
