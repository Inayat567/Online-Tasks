package com.mycompany.sms;

import java.util.ArrayList;

public class Student {

    private static ArrayList<Student> studentList = new ArrayList<>();
    private int id;
    private String name;
    private int age;
    private ArrayList<String> enrolledcourse = new ArrayList<>();

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(Student studentList) {
        Student.studentList.add(studentList);
    }

    public void setEnrolledCourse(String course, int id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).id == id) {
                studentList.get(i).enrolledcourse.add(course);
            }
        }
    }

    public int getAge() {
        return age;
    }

    public ArrayList<String> getEnrolledCourse() {
        return enrolledcourse;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student() {

    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        System.out.println(studentList);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
