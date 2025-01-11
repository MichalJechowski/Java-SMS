package com.smsproject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

/**
 * Główna klasa aplikacji, która zarządza interfejsem użytkownika oraz operacjami na studentach.
 * Obejmuje połączenie z bazą danych, dodawanie, usuwanie, aktualizowanie i wyświetlanie danych studentów,
 * a także obliczanie średniej ocen studentów.
 */
public class Main {

    /**
     * Główna metoda aplikacji, która uruchamia połączenie z bazą danych i GUI.
     *
     * @param args Argumenty wejściowe z linii poleceń (nieużywane w tej klasie).
     */
    public static void main(String[] args) {

        // Implementacja
        String url = "jdbc:sqlite:resource:mydatabase.db";
        StudentManagerImpl studentManagerImpl = new StudentManagerImpl();
        studentManagerImpl.connectToDB(url);

        // GUI
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Students Table");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 500);

            JPanel panel = createPanel(studentManagerImpl);

            frame.add(panel, BorderLayout.CENTER);

            frame.setVisible(true);
        });

    }

    /**
     * Tworzy panel GUI, na którym znajdują się pola tekstowe, przyciski oraz tabela studentów.
     *
     * @param studentManagerImpl Obiekt zarządzający studentami.
     * @return Panel GUI.
     */
    private static JPanel createPanel(StudentManagerImpl studentManagerImpl) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // studentIDLabel
        JLabel studentIDLabel = new JLabel("StudentID:");
        studentIDLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField studentIDField = new JTextField(15);
        studentIDField.setMaximumSize(new Dimension(Integer.MAX_VALUE, studentIDField.getPreferredSize().height));

        // nameLabel
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField nameField = new JTextField(15);
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, nameField.getPreferredSize().height));

        // ageLabel
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField ageField = new JTextField(15);
        ageField.setMaximumSize(new Dimension(Integer.MAX_VALUE, ageField.getPreferredSize().height));

        // gradeLabel
        JLabel gradeLabel = new JLabel("Grade:");
        gradeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField gradeField = new JTextField(15);
        gradeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, gradeField.getPreferredSize().height));

        // AddButton
        JButton addButton = new JButton("Add student");
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    if (name == null || name.trim().isEmpty()) {
                        throw new IllegalArgumentException("Name cannot be empty");
                    }

                    String randomID = String.valueOf((int) (Math.random() * 1000)) + name;

                    int age;
                    try {
                        age = Integer.parseInt(ageField.getText());
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Invalid age. Please enter a valid number.");
                    }

                    double grade;
                    try {
                        grade = Double.parseDouble(gradeField.getText());
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Invalid grade. Please enter a valid decimal number.");
                    }

                    studentManagerImpl.addStudent(new Student(name, age, grade, randomID));
                    throwDialog("Success");

                } catch (IllegalArgumentException ex) {
                    throwDialog("Error: " + ex.getMessage());
                } catch (Exception ex) {
                    throwDialog("An unexpected error occurred: " + ex.getMessage());
                }
            }
        });

        // RemoveButton
        JButton removeButton = new JButton("Remove student");
        removeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = studentIDField.getText();

                try {
                    // Validate studentID input
                    if (studentID == null || studentID.trim().isEmpty()) {
                        throw new IllegalArgumentException("Student ID cannot be empty.");
                    }

                    // Attempt to remove the student
                    studentManagerImpl.removeStudent(studentID);

                    // Success dialog
                    throwDialog("Student removed successfully.");
                } catch (IllegalArgumentException ex) {
                    // User-related issues
                    throwDialog("Error: " + ex.getMessage());
                } catch (Exception ex) {
                    // Catch any unexpected issues
                    throwDialog("An unexpected error occurred: " + ex.getMessage());
                }
            }
        });

        // DisplayAllStudentsButton
        JButton openButton = new JButton("Display all students");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame newFrame = new JFrame("New Frame");
                newFrame.setSize(400, 200);
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JScrollPane tablePanel = createTablePanel(studentManagerImpl);
                newFrame.add(tablePanel, BorderLayout.CENTER);
                newFrame.setVisible(true);
            }
        });

        // UpdateStudentBtn
        JButton updateButton = new JButton("Update student");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Retrieve input values
                    String studentID = studentIDField.getText();
                    String name = nameField.getText();
                    String ageText = ageField.getText();
                    String gradeText = gradeField.getText();

                    // Validate studentID
                    if (studentID == null || studentID.trim().isEmpty()) {
                        throw new IllegalArgumentException("Student ID cannot be empty.");
                    }

                    // Validate name
                    if (name == null || name.trim().isEmpty()) {
                        throw new IllegalArgumentException("Name cannot be empty.");
                    }

                    // Validate and parse age
                    int age;
                    try {
                        age = Integer.parseInt(ageText);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Invalid age. Please enter a valid number.");
                    }

                    // Validate and parse grade
                    double grade;
                    try {
                        grade = Double.parseDouble(gradeText);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Invalid grade. Please enter a valid decimal number.");
                    }

                    // Update student details
                    studentManagerImpl.updateStudent(studentID, name, age, grade);

                    // Success dialog
                    throwDialog("Student updated successfully.");
                } catch (IllegalArgumentException ex) {
                    // User-related issues
                    throwDialog("Error: " + ex.getMessage());
                } catch (Exception ex) {
                    // Catch any unexpected issues
                    throwDialog("An unexpected error occurred: " + ex.getMessage());
                }
            }
        });

        // CalculateAverageGradeBtn
        JButton calculateButton = new JButton("Calculate grade of students");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throwDialog("Avarage grade of students: " + String.valueOf(studentManagerImpl.calculateAverageGrade()));
            }
        });

        panel.add(studentIDLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(studentIDField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(nameLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(nameField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(ageLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(ageField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(gradeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(gradeField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(addButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(removeButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(updateButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(openButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(calculateButton);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        return panel;
    }

    /**
     * Tworzy panel z tabelą, która wyświetla wszystkich studentów.
     *
     * @param studentManagerImpl Obiekt zarządzający studentami.
     * @return Panel z tabelą.
     */
    private static JScrollPane createTablePanel(StudentManagerImpl studentManagerImpl) {

        ArrayList<Student> students = studentManagerImpl.displayAllStudents();
        String[] columnNames = { "Name", "Age", "Grade", "Student ID" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Student student : students) {
            model.addRow(new Object[] { student.getName(), student.getAge(), student.getGrade(),
                    student.getStudentID() });
        }

        JTable table = new JTable(model);
        JScrollPane tablePanel = new JScrollPane(table);

        return tablePanel;
    }

    /**
     * Wyświetla okno dialogowe z wiadomością.
     *
     * @param message Wiadomość, która ma zostać wyświetlona w oknie dialogowym.
     * @return Nowe okno dialogowe z wiadomością.
     */
    private static JFrame throwDialog(String message) {
        JFrame newFrame = new JFrame("Message");
        newFrame.setSize(300, 200);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        newFrame.add(label);
        newFrame.setVisible(true);

        return newFrame;
    }
}
