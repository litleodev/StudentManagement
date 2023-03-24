package com.studentmanagement.controller;

import com.studentmanagement.enums.Gender;
import com.studentmanagement.enums.Subject;
import com.studentmanagement.model.StudentCreateRequest;
import com.studentmanagement.model.StudentUpdateRequest;
import com.studentmanagement.service.StudentService;

import java.util.Scanner;

public class StudentController {
    private final Scanner scanner;
    private final StudentService service;

    public StudentController(){
        this.scanner = new Scanner(System.in);
        this.service = StudentService.getstudentService();
    }

    // Set id when updated, deleted student
    private int setId(){
        System.out.println("Enter ID of student:");
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
    private String setFullName(){
        String fullName;
        System.out.println("Enter full name of student:");
        fullName = scanner.nextLine();
        if(fullName.isBlank())
        {
            fullName = setFullName();
        }
        return fullName;
    }
    // Set gender when created, updated student
    private int setGender(){
        System.out.println(Gender.FEMALE.getGenderValue() + ". Female");
        System.out.println(Gender.MALE.getGenderValue() + ". Male");
        System.out.println(Gender.OTHER.getGenderValue() + ". Other");
        System.out.println("Enter gender of student:");
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
    private int setAge(){
        System.out.println("Enter age of student:");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e){
            age = setAge();
        }
        if(age < 1)
            age = setAge();

        return age;
    }
    // Set subject scores (Math, Physics, Chemistry) when created, updated student
    private double setSubjectScores(Subject subject){
        System.out.println(String.format("Enter %s scores of student:", subject.getSubjectName()));
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

    private boolean confirmRemoveStudent(String studentName){
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

    public void createStudent(){
        var request = new StudentCreateRequest();

        request.setFullName(setFullName());
        request.setGender(setGender());
        request.setAge(setAge());
        request.setMath(setSubjectScores(Subject.MATH));
        request.setPhysics(setSubjectScores(Subject.PHYSICS));
        request.setChemistry(setSubjectScores(Subject.CHEMISTRY));

        System.out.println(service.createStudent(request));
    }

    public void updateStudent(){
        var id = setId();
        var student = service.getById(id);
        if(student == null)
            System.out.println("This student is not exist!");
        var request = new StudentUpdateRequest(id,
                student.getFullName(),
                student.getGender(),
                student.getAge(),
                student.getMath(),
                student.getPhysics(),
                student.getChemistry());
        int select = -1;
        while (select != 0){
            System.out.println(String.format("Student: %s - ID: %d", student.getFullName(), student.getId()));
            System.out.println(String.format("1. Update full name: old: %s - new: %s", student.getFullName(), request.getFullName()));
            System.out.println(String.format("2. Update gender: old: %s - new: %s", Gender.getGenderName(student.getGender()), Gender.getGenderName(request.getGender())));
            System.out.println(String.format("3. Update age: old: %s - new: %s", student.getAge(), request.getAge()));
            System.out.println(String.format("4. Update Math scores: old: %.2f - new: %.2f", student.getMath(), request.getMath()));
            System.out.println(String.format("5. Update Physics scores: old: %.2f - new: %.2f", student.getPhysics(), request.getPhysics()));
            System.out.println(String.format("6. Update Chemistry scores: old: %.2f - new: %.2f", student.getChemistry(), request.getChemistry()));
            System.out.println("7. Save");
            System.out.println("0. Back");
            System.out.println("Choose service:");
            try{
                select = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                select = -1;
            }
            switch (select){
                case 1:
                    request.setFullName(setFullName());
                    break;
                case 2:
                    request.setGender(setGender());
                    break;
                case 3:
                    request.setAge(setAge());
                    break;
                case 4:
                    request.setMath(setSubjectScores(Subject.MATH));
                    break;
                case 5:
                    request.setPhysics(setSubjectScores(Subject.PHYSICS));
                    break;
                case 6:
                    request.setChemistry(setSubjectScores(Subject.CHEMISTRY));
                    break;
                case 7:
                    System.out.println(service.updateStudent(request));
                    break;
                default:
                    break;
            }
        }
    }

    public void removeStudent(){
        var id = setId();
        var student = service.getById(id);
        if(student == null)
            System.out.println("This student is not exist!");

        var isConfirm = confirmRemoveStudent(student.getFullName());
        if(isConfirm)
            System.out.println(service.removeStudent(id));
    }
}
