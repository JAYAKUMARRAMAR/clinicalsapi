//Patient jpa model class with primary key id,first name,last name and age
package com.jaya.patientclinicals.clinicalsapi.clinicalsapi.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, 
        fetch=FetchType.EAGER)
    private List<ClinicalData> clinicalData;

    public Patient() {
    }

    public Patient(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}