package com.studentmanagement;

import com.studentmanagement.controller.StudentController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var controller = new StudentController();
        Scanner scanner = new Scanner(System.in);
        int select = -1;
        while (select != 0){
            System.out.println("Student management system");
            System.out.println("1. Create new student");
            System.out.println("2. Update information of student");
            System.out.println("3. Remove student");
            System.out.println("4. Search student by name");
            System.out.println("5. Display students");
            System.out.println("0. Exit");
            System.out.println("Choose service:");
            try{
                select = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                select = -1;
            }
            switch (select){
                case 1:
                    controller.createStudent();
                    break;
                case 2:
                    controller.updateStudent();
                    break;
                case 3:
                    controller.removeStudent();
                    break;
                case 4:
                    controller.searchStudent();
                    break;
                case 5:
                    controller.displayStudents();
                    break;
                default:
                    break;
            }
        }
    }
}
