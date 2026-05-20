package com.welaCab;

import jakarta.persistence.*;

@Entity
@Table(name = "riders")

public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String phoneNumber;


    //getter methods
    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }


    //setter methods
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}
