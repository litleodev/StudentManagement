package com.studentmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor
public class StudentUpdateRequest {
    private int id;
    private String fullName;
    private int gender;
    private LocalDateTime dob;
    private double math;
    private double physics;
    private double chemistry;
}
