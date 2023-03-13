package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {


    HashMap<String, Student> studentHashMap = new HashMap<>();
    HashMap<String, Teacher> teacherHashMap = new HashMap<>();
    HashMap<String, List<String>> studentTeacherHashMap = new HashMap<>();

    public void addStudent(Student student) {
        studentHashMap.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherHashMap.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String studentName, String teacherName) {
        if (studentTeacherHashMap.containsKey(teacherName)) {
            studentTeacherHashMap.get(teacherName).add(studentName);
        } else {
            List<String> l = new ArrayList<>();
            l.add(studentName);
            studentTeacherHashMap.put(teacherName, l);
        }
    }

    public Student getStudentByName(String studentName) {
        return studentHashMap.get(studentName);
    }

    public Teacher getTeacherByName(String teacherName) {
        return teacherHashMap.get(teacherName);
    }

    public List<String> getStudentsByTeacherName(String teacherName) {
        return studentTeacherHashMap.get(teacherName);
    }

    public List<String> getAllStudents() {
        // return new ArrayList<>(studentHashMap.keySet());

        List<String> student = new ArrayList<>();
        for(String s : studentHashMap.keySet())
        {
            student.add(s);
        }
        return student;
    }

    public void deleteTeacherByName(String teacherName) {
        List<String> l = new ArrayList<>(studentTeacherHashMap.get(teacherName));

        studentTeacherHashMap.remove(teacherName);
        teacherHashMap.remove(teacherName);
        for (String s : l) {
            studentHashMap.remove(s);
        }
    }

    public void deleteAllTeachers() {
        List<String> l = new ArrayList<>();
        for (String s : studentTeacherHashMap.keySet()) {
            l.addAll(studentTeacherHashMap.get(s));
        }

        studentTeacherHashMap.clear();
        teacherHashMap.clear();
        for (String s : l) {
            studentHashMap.remove(s);
        }
    }
}