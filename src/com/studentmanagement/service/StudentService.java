package com.studentmanagement.service;

import com.studentmanagement.entity.Student;
import com.studentmanagement.model.StudentCreateRequest;
import com.studentmanagement.model.StudentDTO;
import com.studentmanagement.model.StudentUpdateRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {
    private static int id;
    private static Map<Integer, Student> students;
    private static StudentService studentService;

    private StudentService() {
    }

    public static StudentService getstudentService() {
        if (studentService == null) {
            id = 1;
            students = new HashMap<>();
            studentService = new StudentService();
        }
        return studentService;
    }

    public String createStudent(StudentCreateRequest request){
        try {
            var student = new Student(request.getFullName(),
                    request.getGender(),
                    request.getDob(),
                    request.getMath(),
                    request.getPhysics(),
                    request.getChemistry());

            students.put(id, student);
            ++id;
            return "Create new student success!";
        }
        catch (Exception e){
            return "Cannot create new student";
        }
    }

    public StudentDTO getById(int id){
        var student = students.get(id);
        if(student == null)
            return null;
        return new StudentDTO(id, student.getFullName(),
                student.getGender(),
                student.getDob(),
                student.getMath(),
                student.getPhysics(),
                student.getChemistry());
    }

    public String updateStudent(StudentUpdateRequest request){
        try {
            var student = new Student(request.getFullName(),
                    request.getGender(),
                    request.getDob(),
                    request.getMath(),
                    request.getPhysics(),
                    request.getChemistry());

            students.replace(request.getId(), student);
            return "Update student success!";
        }
        catch (Exception e){
            return "Cannot update student";
        }
    }

    public String removeStudent(int id){
        try{
            var isExist = students.containsKey(id);
            if(!isExist)
                return "Student is not exist";
            students.remove(id);
            return "Remove student success!";
        }
        catch (Exception e){
            return "Cannot remove student";
        }
    }

    public List<StudentDTO> getByName(String name){
        List<StudentDTO> result = new ArrayList<>();
        if(name.isBlank())
            return result;
        for (var entry : students.entrySet())
        {
            var student = entry.getValue();
            if(student.getFullName().toLowerCase().equals(name.toLowerCase())){
                result.add(new StudentDTO(entry.getKey(),
                        student.getFullName(),
                        student.getGender(),
                        student.getDob(),
                        student.getMath(),
                        student.getPhysics(),
                        student.getChemistry()));
            }
        }
        return result;
    }
}
