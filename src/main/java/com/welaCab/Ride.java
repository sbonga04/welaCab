package com.welaCab;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String riderPhone;
    private String driverPhone;
    private String pickup;
    private String dropOff;
    private String status; // pending, accepted, completed
    private LocalDateTime createdAt;

    //@PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }

    //getter methods
    public long getId() {
        return id;
    }

    public String getRiderPhone() {
        return riderPhone;
    }

    public String getDriverPhone() {
        return driverPhone;
    }
    public String getPickup() {
        return pickup;
    }
    public String getDropOff() {
        return dropOff;
    }
    public String getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }



    //setter
    public void setId(long id){this.id = id; }
    public void setRiderPhone(String riderPhone) {
        this.riderPhone = riderPhone;
    }
    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }
    public void setDropOff(String dropOff) {
        this.dropOff = dropOff;
    }
    public void setPickup(String pickup) {
        this.pickup = pickup;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setStatus(String status) {
        this.status = status;
    }


}
