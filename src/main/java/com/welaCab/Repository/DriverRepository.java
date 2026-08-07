package com.welaCab.Repository;

import com.welaCab.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Long> {
    Driver findByPhoneNumber(String phoneNumber);

}
