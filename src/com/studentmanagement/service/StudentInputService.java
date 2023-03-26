package com.studentmanagement.service;

import com.studentmanagement.enums.Gender;
import com.studentmanagement.enums.Subject;

import java.time.LocalDateTime;
import java.util.Scanner;

public class StudentInputService {
    private final Scanner scanner;

    public StudentInputService(){
        scanner = new Scanner(System.in);
    }

    public int select(){
        int selection;
        try {
            selection = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            selection = -1;
        }
        return selection;
    }

    // Set id when updated, deleted student
    public int setId(){
        System.out.println("Enter ID:");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            id = setId();
        }
        if(id < 1)
            id = setId();

        return id;
    }

    // Set full name when created, updated student
    public String setFullName(){
        String fullName;
        System.out.println("Enter full name:");
        fullName = scanner.nextLine();
        if(fullName.isBlank())
        {
            fullName = setFullName();
        }
        return fullName;
    }
    // Set gender when created, updated student
    public int setGender(){
        System.out.println(Gender.FEMALE.value + ". Female");
        System.out.println(Gender.MALE.value + ". Male");
        System.out.println(Gender.OTHER.value + ". Other");
        System.out.println("Enter gender:");
        int gender;
        try {
            gender = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            gender = 0;
        }
        if(!Gender.isDefine(gender)){
            gender = setGender();
        }
        return gender;
    }
    // Set age when created, updated student
    public LocalDateTime setDob(){
        System.out.println("Enter date of birth (day/month/year):");
        LocalDateTime dob;
        String input = scanner.nextLine();
        String[] strInput = input.split("/");
        if(strInput.length != 3)
            dob = setDob();
        try {
            int day = Integer.parseInt(strInput[0]);
            int month = Integer.parseInt(strInput[1]);
            int year = Integer.parseInt(strInput[2]);
            dob = LocalDateTime.of(year, month, day, 0, 0);
        }
        catch (Exception e){
            dob = setDob();
        }

        if(dob.isAfter(LocalDateTime.now()))
            dob = setDob();

        return dob;
    }
    // Set subject scores (Math, Physics, Chemistry) when created, updated student
    public double setSubjectScores(Subject subject){
        System.out.println(String.format("Enter %s scores:", subject.getSubjectName()));
        double scores;
        try {
            scores = Double.parseDouble(scanner.nextLine());
        }
        catch (NumberFormatException e){
            scores = setSubjectScores(subject);
        }
        if(scores < 0 || scores > 10)
            scores = setSubjectScores(subject);

        return scores;
    }

    public boolean confirmRemoveStudent(String studentName){
        System.out.println(String.format("Do you want to remove student: %s!", studentName));
        System.out.println("Y/N");
        String selection = scanner.nextLine();
        boolean result;
        if(selection.isBlank()){
            selection = selection.toLowerCase();
            if(selection == "y")
                result = true;
            else if (selection == "n")
                result = false;
            else
                result = confirmRemoveStudent(studentName);
        }else{
            result = confirmRemoveStudent(studentName);
        }
        return result;
    }

    public String setName(){
        String name;
        System.out.println("Enter name of Student:");
        name = scanner.nextLine();
        if(name.isBlank())
        {
            name = setName();
        }
        return name;
    }
}
