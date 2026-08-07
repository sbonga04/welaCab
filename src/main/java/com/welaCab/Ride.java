package com.welaCab;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "rides")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String driverPhoneNumber;
    private String riderPhoneNumber;
    private String pickup;
    private String dropOff;
    private String status;
    private LocalDateTime createdAt;

    public void setId(Long id) {this.id = id;}
    public void setStatus(String status) {this.status = status;}
    public void setPickup(String pickup) {this.pickup = pickup;}
    public void setDriverPhoneNumber(String phoneNumber) {this.driverPhoneNumber = phoneNumber;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
    public void setDropOff(String dropOff) {this.dropOff = dropOff;}
    public void setRiderPhoneNumber(String riderPhoneNumber) {this.riderPhoneNumber = riderPhoneNumber;}

    public Long getId(){return id;}
    public String getStatus() {return status;}
    public LocalDateTime getCreatedAt() {return createdAt;}
    public String getPickup() {return pickup;}
    public String getDriverPhoneNumber() {return driverPhoneNumber;}
    public String getRiderPhoneNumber() {return riderPhoneNumber;}
    public String getDropOff() {return dropOff;}
}
