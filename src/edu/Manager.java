package edu;

import java.util.ArrayList;
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

    public void addStudent(String studentId, String studentFirstName, String studentLastName) {
        students.add(new Student(studentId, studentFirstName, studentLastName));
    }

    public void addProfessor(String professorFirstName, String professorLastName, String professorRank) {
        professors.add(new Professor(professorFirstName, professorLastName, professorRank));
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
}
