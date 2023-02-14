package com.example.coursera_project.models;

import com.example.coursera_project.models.Instructors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_time")
    private int totalTime;

    @Column(name = "credit")
    private int credit;

    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructors instructors;

//    @ManyToMany(mappedBy = "courses")
//    private List<Students> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Instructors getInstructors() {
        return instructors;
    }

    public void setInstructors(Instructors instructors) {
        this.instructors = instructors;
    }
}
