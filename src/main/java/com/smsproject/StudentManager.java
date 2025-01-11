package com.smsproject;
import java.util.ArrayList;

/**
 * Interfejs do zarządzania studentami w systemie.
 * Definiuje metody do dodawania, usuwania, aktualizowania oraz wyświetlania studentów,
 * a także do obliczania średniej ocen.
 */
public interface StudentManager {

    /**
     * Dodaje nowego studenta do systemu.
     *
     * @param student Obiekt reprezentujący studenta, który ma zostać dodany.
     */
    void addStudent(Student student);

    /**
     * Usuwa studenta z systemu na podstawie jego identyfikatora.
     *
     * @param studentID Identyfikator studenta, który ma zostać usunięty.
     */
    void removeStudent(String studentID);

    /**
     * Aktualizuje dane studenta na podstawie jego identyfikatora.
     *
     * @param studentID Identyfikator studenta, którego dane mają zostać zaktualizowane.
     * @param newName Nowe imię studenta.
     * @param newAge Nowy wiek studenta.
     * @param newGrade Nowa ocena studenta.
     */
    void updateStudent(String studentID, String newName, int newAge, double newGrade);

    /**
     * Wyświetla wszystkich studentów w systemie.
     *
     * @return Lista wszystkich studentów w systemie.
     */
    ArrayList<Student> displayAllStudents();

    /**
     * Oblicza średnią ocen wszystkich studentów.
     *
     * @return Średnia ocena wszystkich studentów.
     */
    double calculateAverageGrade();
}
