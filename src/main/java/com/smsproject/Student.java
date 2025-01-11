package com.smsproject;

/**
 * Klasa reprezentująca studenta w systemie zarządzania studentami.
 * Przechowuje informacje o imieniu, wieku, ocenie i identyfikatorze studenta.
 */
public class Student {
    private String name;
    private int age;
    private double grade;
    private String studentID;

    /**
     * Konstruktor klasy Student.
     *
     * @param name      Imię studenta.
     * @param age       Wiek studenta.
     * @param grade     Ocena studenta.
     * @param studentID Identyfikator studenta.
     */
    public Student(String name, int age, double grade, String studentID) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.studentID = studentID;
    }

    /**
     * Wyświetla informacje o studencie w konsoli.
     */
    public void displayInfo() {
        System.out.println(
                "Name: " + this.name + "\n" +
                        "age: " + this.age + "\n" +
                        "grade: " + this.grade + "\n" +
                        "studentID: " + this.studentID + "\n"
        );
    }

    /**
     * Zwraca imię studenta.
     *
     * @return Imię studenta.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Zwraca wiek studenta.
     *
     * @return Wiek studenta.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Zwraca ocenę studenta.
     *
     * @return Ocena studenta.
     */
    public double getGrade() {
        return this.grade;
    }

    /**
     * Zwraca identyfikator studenta.
     *
     * @return Identyfikator studenta.
     */
    public String getStudentID() {
        return this.studentID;
    }

    /**
     * Ustawia imię studenta.
     *
     * @param name Nowe imię studenta.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Ustawia wiek studenta.
     *
     * @param age Nowy wiek studenta.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Ustawia ocenę studenta.
     *
     * @param grade Nowa ocena studenta.
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * Ustawia identyfikator studenta.
     *
     * @param studentID Nowy identyfikator studenta.
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
