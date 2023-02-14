package com.example.coursera_project.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;

@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pin")
    private int pin;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;


    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @ManyToMany
    @JoinTable(name = "students courses xref",
            joinColumns = @JoinColumn(name = "students_pin"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private HashSet<Courses> courses;

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
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

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

}
