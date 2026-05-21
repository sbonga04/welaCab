package com.welaCab.repository;

import com.welaCab.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver,Long> {
    Driver findByPhoneNumber(String phoneNumber);
    List<Driver> findByAvailableTrue();
}
