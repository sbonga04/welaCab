package com.welaCab.repository;

import com.welaCab.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider,Long> {
    Rider findByPhoneNumber(String phoneNumber);
}
