package com.studentmanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;

@Getter @Setter
public class StudentDTO {
    private int id;
    private String fullName;
    private int gender;
    private LocalDate dob;
    private double math;
    private double physics;
    private double chemistry;

    public StudentDTO(int id, String fullName, int gender, LocalDate dob, double math, double physics, double chemistry) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.math = math;
        this.physics = physics;
        this.chemistry = chemistry;
    }

    public int getAge(){
        LocalDate now = LocalDate.now();
        LocalDate dateOfBirthThisYear = LocalDate.of(now.getYear(), this
                .dob.getMonth(), dob.getDayOfMonth());
        return now.isAfter(dateOfBirthThisYear)
                ? now.getYear() - this.dob.getYear()
                : now.getYear() - this.dob.getYear() - 1;

    }

    public double getAverage(){
        return (this.math + this.physics + this.chemistry) / 3;
    }

    public String getClassification(){
        double average = getAverage();
        return average >= 8
                ? "Very good" : average >= 6.5
                ? "Good" : average >= 5
                ? "Average" : "Bad";
    }
}
