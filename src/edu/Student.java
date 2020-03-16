package edu;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private HashMap<Course, CourseInfo> coursesThisSemester;
    private HashMap<Course, CourseInfo> coursesPassed;

    public Student(String studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        coursesThisSemester = new HashMap<>();
        coursesPassed = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getStudentId() {
        return studentId;
    }

    public HashMap<Course, CourseInfo> getCoursesThisSemester() {
        return coursesThisSemester;
    }

    public Boolean hasCourse(Course course) {
        if (coursesThisSemester.containsKey(course))
            return true;
        else {
            for (Course passedCourse : coursesPassed.keySet()) {
                if (passedCourse.getName().equals(course.getName()))
                    return true;
            }
            return false;
        }
    }

    public Boolean hasPassedPreCourses(Course course) {
        ArrayList<String> preCoursesName = course.getPreCourses();
        for (Course passedCourse : coursesPassed.keySet()) {
            for (String preCourseName : preCoursesName) {
                if (passedCourse.getName().equals(preCourseName)) {
                    preCoursesName.remove(preCourseName);
                    break;
                }
            }
        }
        return preCoursesName.isEmpty();
    }

    public void takeCourse(Course course) {
        coursesThisSemester.put(course, new CourseInfo());
    }

    public void dropCourse(Course course) {
        coursesThisSemester.remove(course);
    }

    public void submitCourseMark(Course course, float mark) {
        for (Course courseLoop : coursesThisSemester.keySet()) {
            if (courseLoop.getName().equals(course.getName())) {
                CourseInfo newCourseInfo = coursesThisSemester.get(courseLoop);
                newCourseInfo.setMark(mark);
                coursesThisSemester.replace(courseLoop, newCourseInfo);
                break;
            }
        }
    }

    public void passCourses() {
        for (Course course : coursesThisSemester.keySet()) {
            CourseInfo courseInfo = coursesThisSemester.get(course);
            if (courseInfo.getMark() >= 10) {
                coursesPassed.put(course, courseInfo);
            }
        }
        coursesThisSemester.clear();
    }

}
