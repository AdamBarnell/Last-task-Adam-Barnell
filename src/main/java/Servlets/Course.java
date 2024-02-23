package Servlets;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int id;
    private String name;
    private String description;
    private int YHP;
    private List<String> studentNames = new ArrayList<>();
    private List<String> teacherNames = new ArrayList<>();


    public Course(int id, String name, String description, int YHP) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.YHP = YHP;
    }

    public void addStudentName(String studentName) {
        this.studentNames.add(studentName);
    }

    public void addTeacherName(String teacherName) {
        this.teacherNames.add(teacherName);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getYHP() {
        return YHP;
    }

    public List<String> getStudentNames() {
        return studentNames;
    }

    public List<String> getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(List<String> teacherNames) {
        this.teacherNames = teacherNames;
    }
}
