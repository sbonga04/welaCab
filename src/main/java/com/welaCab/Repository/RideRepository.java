package com.welaCab.Repository;

import com.welaCab.Driver;
import com.welaCab.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride,Long> {
    Driver findByDriverPhoneNumberAndStatus(String phoneNumber,String status);
    Ride findByRiderPhoneNumberAndStatus(String phoneNumber,String status);
}
