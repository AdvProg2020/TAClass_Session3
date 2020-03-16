package edu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CommandProcessor {
    private Manager manager;
    private Scanner scanner;

    public CommandProcessor(Manager manager) {
        this.manager = manager;
        scanner = new Scanner(System.in);
    }

    private void processAddStudent(String[] splitInput) {
        manager.addStudent(splitInput[2], splitInput[3], splitInput[4]);
    }

    private void processAddProfessor(String[] splitInput) {
        manager.addProfessor(splitInput[2], splitInput[3], splitInput[4]);
    }

    private void processAddCourse(String[] splitInput) {
        ArrayList<String> preCourses = new ArrayList<>();
        if (splitInput.length == 6)
            Collections.addAll(preCourses, splitInput[5].split(","));
        manager.addCourse(splitInput[2], splitInput[3], splitInput[4], preCourses);
    }

    private void processShowStudents() {
        for (Student student : manager.getStudents()) {
            System.out.println(student);
        }
    }

    private void processShowProfessors() {
        for (Professor professor : manager.getProfessors()) {
            System.out.println(professor);
        }
    }

    private void processShowThisSemesterCourses() {
        for (Course course : manager.getCoursesThisSemester()) {
            System.out.println(course);
        }
    }

    private void processTakeCourse(String[] splitInput) {
        if (manager.takeCourseForStudent(splitInput[2], splitInput[3]))
            System.out.println("Successfully took course!");
        else
            System.out.println("Failed to take course!");
    }

    private void processShowCoursesForStudent(String[] splitInput) {
        for (Course course : manager.getStudentCoursesThisSemester(splitInput[4])) {
            System.out.println(course);
        }
    }

    private void processDropCourse(String[] splitInput) {
        manager.dropCourseForStudent(splitInput[2], splitInput[3]);
    }

    public void run() {
        String input;
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            if (input.startsWith("add student")) {
                processAddStudent(input.split("\\s"));
            } else if (input.startsWith("add professor")) {
                processAddProfessor(input.split("\\s"));
            } else if (input.startsWith("add course")) {
                processAddCourse(input.split("\\s"));
            } else if (input.startsWith("show students")) {
                processShowStudents();
            } else if (input.startsWith("show professors")) {
                processShowProfessors();
            } else if (input.startsWith("show courses this semester")) {
                processShowThisSemesterCourses();
            } else if (input.startsWith("take course")) {
                processTakeCourse(input.split("\\s"));
            } else if (input.startsWith("drop course")) {
                processDropCourse(input.split("\\s"));
            } else if (input.startsWith("show courses for student")) {
                processShowCoursesForStudent(input.split("\\s"));
            }
            else {
                System.err.println("invalid command");
            }
        }
    }
}
