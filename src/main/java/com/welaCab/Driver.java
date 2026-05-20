package com.welaCab;

import jakarta.persistence.*;

@Entity
@Table (name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String name;
    private String phoneNumber;
    private String vehicleName;
    private String plateNumber;
    private boolean Available;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getVehicleName() {
        return vehicleName;
    }
    public String getPlateNumber() {
        return plateNumber;
    }

    public boolean isAvailable() {
        return Available;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setAvailable(boolean available) {
        Available = available;
    }
}
