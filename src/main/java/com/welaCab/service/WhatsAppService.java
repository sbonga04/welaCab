package com.welaCab.service;

import com.welaCab.Driver;
import com.welaCab.Rider;
import com.welaCab.repository.DriverRepository;
import com.welaCab.repository.RideRepository;
import com.welaCab.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    @Autowired
    private RiderRepository riderRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private RideRepository rideRepository;

    public void handleMessage(String from, String message) {
        message = message.toLowerCase().trim();

        //this is checking if a user exist as rider or driver
        Rider rider = riderRepository.findByPhoneNumber(from);
        Driver driver = driverRepository.findByPhoneNumber(from);


        if (rider == null && driver == null) {
            handleNewUser(from, message);
        }

        if (driver != null)
            handleDriver(driver, message);

        handleRider(rider, message);
        //return "";
    }

    private String handleNewUser(String from,String message){
        if (message.contains("rider")) {
            Rider newRider = new Rider();
            newRider.setPhoneNumber(from);
            newRider.setName("New Rider");
            riderRepository.save(newRider);
            return "Welcome to welaCab! You're registered as a rider. Type 'ride' to request a ride.";
        } else if (message.contains("driver")) {
            return "Welcome! To register as a driver please reply with your name, Vehicle make and plate Number separated by commas. E.g John,Toyota,Corolla,BY435ZN";
        }else
            return "Welcome to welaCab, are you a *Rider* or a *Driver*";
    }

    private String handleRider(Rider rider,String message){
        if (message.contains("ride")){
            return "Sure!, Where are you being picked up from?";
        }
        return "Type 'ride' to request a ride.";
    }
    private String handleDriver(Driver driver, String message){
        if (message.contains("online")) {
            driver.setAvailable(true);
            driverRepository.save(driver);
            return "You are now online and will start receiving ride requests";
        } else if (message.contains("offline")) {
            driver.setAvailable(false);
            driverRepository.save(driver);
            return "You are now offline";
            
        }
        return "Type 'online' to start receiving rider or 'offline' to stop";
    }
}
