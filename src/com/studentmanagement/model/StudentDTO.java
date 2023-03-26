package com.studentmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor
public class StudentDTO {
    private int id;
    private String fullName;
    private int gender;
    private LocalDateTime dob;
    private double math;
    private double physics;
    private double chemistry;

    public int getAge(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateOfBirthThisYear = LocalDateTime.of(now.getYear(),
                this.dob.getMonth(),
                this.dob.getDayOfMonth(),
                0,0);
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
