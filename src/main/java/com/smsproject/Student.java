package com.smsproject;

public class Student {
    private String name; 
    private int age;
    private double grade;
    private String studentID;

    //Konstruktor
    public Student(String name, int age, double grade, String studentID) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.studentID = studentID;

    }

    //Metody
    public void displayInfo() {
        System.out.println(
        "Name: " + this.name + "\n" +
        "age: " + this.age + "\n" +
        "grade: " + this.grade + "\n" +
        "studentID: " + this.studentID + "\n"
        );
        
    }
    
    //Gettery
    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }
    public double getGrade() {
        return this.grade;
    }
    public String getStudentID() {
        return this.studentID;
    }

    //Settery
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}