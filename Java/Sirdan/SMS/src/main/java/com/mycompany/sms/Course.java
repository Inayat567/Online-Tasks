package com.mycompany.sms;

import java.util.ArrayList;

public class Course {
    private static ArrayList<String[]> courseList = new ArrayList<>();
    private String name;

    public Course(String[] name) {
        Course.courseList.add(name);
    }

    public String getName() {
        return name;
    }
}
