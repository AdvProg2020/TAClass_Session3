package edu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class Manager {
    private String currentSemester;
    private ArrayList<Student> students;
    private ArrayList<Professor> professors;
    private ArrayList<Course> coursesThisSemester;
    private ArrayList<Course> coursesHistory;

    public Manager(String currentSemester) {
        this.currentSemester = currentSemester;
        students = new ArrayList<>();
        professors = new ArrayList<>();
        coursesThisSemester = new ArrayList<>();
        coursesHistory = new ArrayList<>();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public ArrayList<Course> getCoursesThisSemester() {
        return coursesThisSemester;
    }

    public ArrayList<Course> getCoursesHistory() {
        return coursesHistory;
    }

    public void addStudent(String studentId, String studentFirstName, String studentLastName, String nationalCode) {
        students.add(new Student(studentId, studentFirstName, studentLastName, nationalCode));
    }

    public void addProfessor(String professorFirstName, String professorLastName, String professorRank, String nationalCode) {
        professors.add(new Professor(professorFirstName, professorLastName, professorRank, nationalCode));
    }

    public void addCourse(String courseName, String professorFirstName, String professorLastName, ArrayList<String> preCourses) {
        for (Professor professor : professors) {
            if (professor.getFirstName().equals(professorFirstName) && professor.getLastName().equals(professorLastName)) {
                coursesThisSemester.add(new Course(courseName, professor, currentSemester, preCourses));
                break;
            }
        }
    }

    private Student getStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId))
                return student;
        }
        return null;
    }

    private Course getCourseByName(String courseName) {
        for (Course course : coursesThisSemester) {
            if (course.getName().equals(courseName))
                return course;
        }
        return null;
    }

    public Boolean takeCourseForStudent(String courseName, String studentId) {
        Student student = getStudentById(studentId);
        Course course = getCourseByName(courseName);
        if(!student.hasCourse(course) && (course.getPreCourses().size() == 0 || student.hasPassedPreCourses(course))) {
            student.takeCourse(course);
            return true;
        }
        return false;
    }

    public void dropCourseForStudent(String courseName, String studentId) {
        Student student = getStudentById(studentId);
        Course course = getCourseByName(courseName);
        student.dropCourse(course);
    }

    public void submitCourseMarkForStudent(String courseName, float mark, String studentId) {
        Student student = getStudentById(studentId);
        Course course = getCourseByName(courseName);
        student.submitCourseMark(course, mark);
    }

    public Set<Course> getStudentCoursesThisSemester(String studentId) {
        Student student = getStudentById(studentId);
        return student.getCoursesThisSemester().keySet();
    }

    public HashMap<Course, CourseInfo> getStudentCoursesInfoThisSemester(String studentId) {
        Student student = getStudentById(studentId);
        return student.getCoursesThisSemester();
    }

    private void updateCurrentSemester() {
        String[] splitCurrentSemester = currentSemester.split("-");
        int year = Integer.parseInt(splitCurrentSemester[0]);
        int term = Integer.parseInt(splitCurrentSemester[1]);
        if (term == 3) {
            term = 1;
            year += 1;
        } else {
            term += 1;
        }
        currentSemester = "" + year + '-' + term;
    }

    public void goNextSemester() {
        for (Student student : students) {
            student.passCourses();
        }
        coursesHistory.addAll(coursesThisSemester);
        coursesThisSemester.clear();
        updateCurrentSemester();
    }

    public String getCurrentSemester() {
        return currentSemester;
    }

    public ArrayList<Course> getCoursesOfSemester(String semester) {
        ArrayList<Course> coursesToReturn = new ArrayList<>();
        for (Course course : coursesHistory) {
            if (course.getSemester().equals(semester))
                coursesToReturn.add(course);
        }
        return coursesToReturn;
    }

    public Person receiveLoan(String nationalCode) {
        Person person;
        for (Student student : students) {
            if (student.nationalCode.equals(nationalCode)) {
                person = student;
                person.receiveLoan();
                return person;
            }
        }
        for (Professor professor : professors) {
            if (professor.nationalCode.equals(nationalCode)) {
                person = professor;
                person.receiveLoan();
                return person;
            }
        }
        return null;
    }
}
