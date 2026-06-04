package com.welaCab.repository;

import com.welaCab.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride,Long> {
    Ride findByRiderPhoneAndStatus(String phoneNumber,String status);
    Ride findByDriverPhoneAndStatus(String driverPhone, String status);
}