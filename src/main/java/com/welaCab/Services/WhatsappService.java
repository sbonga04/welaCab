package com.welaCab.Services;

import com.welaCab.Driver;
import com.welaCab.Repository.DriverRepository;
import com.welaCab.Repository.RideRepository;
import com.welaCab.Repository.RiderRepository;
import com.welaCab.Rider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*==so this is where everything connects together
more like the actual brain of the app, updates are being done here
or improvements

1.Actions contain handling message form the twilio itself
-handle user rider, if message is coming from user
-handle user driver, if message is coming from driver
-handle registration(new users), if you are going be a driver or a rider
 */
@Service
public class WhatsappService {
    @Autowired
    private TwilioService twilioService;
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private RiderRepository riderRepository;

    public String handleNewMessege(String from,String message){
        message = message.toLowerCase().trim();

        //check if the number exist as any of the users
        Rider rider = riderRepository.findByPhoneNumber(from);
        Driver driver = driverRepository.findByPhoneNumber(from);

        if (rider == null && driver == null)
            handleNewUser(from,message);

//        if (driver != null)
//            handleDriver(from,message);
        return driverSide(from,message);
    }
    private String handleNewUser(String from,String message){

        if(message.contains("ride"))
            clientSide(from,message);

        else if (message.contains("driver"))
            /* some block of code */
            driverSide(from,message);

        return "Hi, Welcome to WelaCab, are you a *Rider* or a *Diver*";
    }
    private String registerRider(String from,String message){
        System.out.println("To register as driver please reply with e.g Your name, Car,Number plate, cellNumber separated by comma ,");


        Rider newRider = new Rider();
        newRider.setCellNumber(from);
        newRider.setName("New Rider");
        riderRepository.save(newRider);

        return "";}
    private String registerDriver(String from,String message){

        String[] parts = message.split(",");
        if(parts.length < 3)
            return "Invalid input.. please register using this format Your name, Car,Number plate, cellNumber" ;


//
        return "";}
    private String clientSide(String from,String message){

        return "Welcome to WelaCab! you are registered as a rider. Type ride to request a ride";
    }
    private String driverSide(String from,String message){

        Driver newDriver = new Driver();
        newDriver.setName("New Driver");
        newDriver.setPhoneNumber(from);
        newDriver.setVehicleName("");

        return """
                    Welcome to WelaCab!, To register as driver please reply with your name, Vehicle make and Plate Number
                    separated by commas(,)e.g John, Toyota Corolla, AB123-ZN 
                    """;

    }

    //private void handleMessage(){}


}
