package com.welaCab;

import jakarta.persistence.*;

@Entity
@Table(name = "riders")
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    public String getCellNumber(){
        return phoneNumber;
    }

    public void setId(Long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCellNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
