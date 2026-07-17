package com.welaCab.service;

import com.welaCab.Driver;
import com.welaCab.Ride;
import com.welaCab.Rider;
import com.welaCab.repository.DriverRepository;
import com.welaCab.repository.RideRepository;
import com.welaCab.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.sql.DriverManager.registerDriver;

@Service
public class WhatsAppService {
    @Autowired
    private TwilioService twilioService;
    @Autowired
    private RiderRepository riderRepository;
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private RideRepository rideRepository;

    final private Map<String,String> userSteps = new HashMap<>();
    final private Map<String,String> pickupLocations = new HashMap<>();

    public String handleMessage(String from, String message) {
        message = message.toLowerCase().trim();
        String step = userSteps.getOrDefault(from, "idle");

        if (step.equals("awaiting_driver_details"))
            return registerDriver(from,message);

        //this is checking if a user exist as rider or driver
        Rider rider = riderRepository.findByPhoneNumber(from);
        Driver driver = driverRepository.findByPhoneNumber(from);



        if (rider == null && driver == null) {
           return handleNewUser(from, message);
        }
//        if (driver != null) {
//            return handleDriver(driver, message);
//        }
        return handleRider(rider, message);
    }

    private String registerDriver(String from, String message) {
        String[] parts = message.split(",");
        if (parts.length < 3)
            return "Please use the correct format: Name,Vehicle Make,Plate Number. E.g John,Toyota Corolla,BY435ZN";

        Driver newDriver = new Driver();
        newDriver.setPhoneNumber(from);
        newDriver.setName(parts[0].trim());
        newDriver.setVehicleName(parts[1].trim());
        newDriver.setPlateNumber(parts[2].trim());
        newDriver.setAvailable(false);
        driverRepository.save(newDriver);

        userSteps.remove(from);
        return "Welcome to WelaCab " + parts[0].trim() + "! You're registered as a driver. Type 'online' to start receiving rides ";

    }

    private Map<String,String> pendingDrivers = new HashMap<>();

    private String handleNewUser(String from,String message){
        if (message.contains("rider")) {
            Rider newRider = new Rider();
            newRider.setPhoneNumber(from);
            newRider.setName("New Rider");
            riderRepository.save(newRider);
            return "Welcome to WelaCab! You're registered as a rider. Type 'ride' to request a ride.";
        } else if (message.contains("driver")) {
            userSteps.put(from, "awaiting_driver_details");
            return "Welcome! To register as a driver please reply with your name, Vehicle make and plate Number separated by commas. E.g John,Toyota Corolla,BY435ZN";
        }else
            return "Welcome to WelaCab, are you a *Rider* or a *Driver*";
    }
    private String handleRider(Rider rider,String message){


        String step = userSteps.getOrDefault(rider.getPhoneNumber(),"idle");
        if(step.equals("awaiting_pickup")){
            pickupLocations.put(rider.getPhoneNumber(),message);
            userSteps.put(rider.getPhoneNumber(), "awaiting_dropOff");
            return "Got it! Where are you going?";
        }


        if (step.equals("awaiting_dropOff")){
            String pickup = pickupLocations.get(rider.getPhoneNumber());
            userSteps.put(rider.getPhoneNumber(), "idle");

            //save ride to the database
            Ride newRide = new Ride();
            newRide.setRiderPhone(rider.getPhoneNumber());
            newRide.setPickup(pickup);
            newRide.setDropOff(message);
            newRide.setStatus("pending");
            rideRepository.save(newRide);


            //find available driver
            List<Driver> availableDriver = driverRepository.findByAvailableTrue();
            if (availableDriver.isEmpty())
                return "Sorry, No available drivers at the moment, try again later";
            else {
                Driver driver = availableDriver.get(0);
                twilioService.sendMessage(
                        driver.getPhoneNumber(),
                        "New ride requested!\nPickup: " + pickup + "\nDropOff: " + message + "\nReply YES to accept."
                );
                Driver drivers = availableDriver.get(0);
                newRide.setDriverPhone(drivers.getPhoneNumber());
                rideRepository.save(newRide);

                return "Driver found, We are notifying them now. Please wait";
            }
        }
        if (message.contains("ride")){
            userSteps.put(rider.getPhoneNumber(), "awaiting_pickup");
            return "Sure!, Where are you being picked up from?";
        }
        return "Type 'ride' to request a ride.";
    }

    private String handleDriver(Driver driver, String message){
        //System.out.println("handleDriver called with message: " + message + " for driver: " + driver.getPhoneNumber());//Debugging line
        if (message.contains("yes")){
            Ride pendingRide = rideRepository.findByRiderPhoneAndStatus(driver.getPhoneNumber(), "pending");
            if(pendingRide == null) {
                return "No pending ride found";
            }
            pendingRide.setStatus("accepted");
            rideRepository.save(pendingRide);
            twilioService.sendMessage(
                    pendingRide.getRiderPhone(),
                    "Your driver " + driver.getName() + " is on the way! Vehicle: " + driver.getVehicleName() + ", Plate: " +driver.getPlateNumber()
            );
            return "Ride accepted! Please Head to " + pendingRide.getPickup() + " to puck your rider.";
        }
        if (message.contains("online")) {
            driver.setAvailable(true);
            driverRepository.save(driver);
            //System.out.println("Driver available set to: " + driver.isAvailable());//Debugging line
            return "You are now online and will start receiving ride requests";
        } else if (message.contains("offline")) {
            driver.setAvailable(false);
            driverRepository.save(driver);
            return "You are now offline";

        }
        return "Type 'online' to start receiving rider or 'offline' to stop";
    }

}
