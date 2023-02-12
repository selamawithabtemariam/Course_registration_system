package edu.mum.se.mumsched.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import edu.mum.se.mumsched.domain.Entry;
import edu.mum.se.mumsched.domain.Student;
import edu.mum.se.mumsched.domain.StudentLesson;
import edu.mum.se.mumsched.domain.Track;
import edu.mum.se.mumsched.service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class StudentControllerTest {


    @Test
    @Disabled
    void testShowStudentPage() {

        StudentController studentController = new StudentController();
        studentController.showStudentPage(new ConcurrentModel());
    }


    @Test
    @Disabled
    void testShowNewStudentForm() {

        StudentController studentController = new StudentController();
        studentController.showNewStudentForm(new ConcurrentModel());
    }


    @Test
    @Disabled
    void testAddNewStudent() {

        StudentController studentController = new StudentController();

        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);

        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setEntry(entry);
        student.setFirstName("Jane");
        student.setId(1);
        student.setLastName("Doe");
        student.setStudentLessons(new ArrayList<>());
        student.setTrack(Track.MSD);
        studentController.addNewStudent(student);
    }


    @Test
    @Disabled
    void testAddNewStudent2() {

        StudentController studentController = new StudentController();

        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);
        Student student = mock(Student.class);
        doNothing().when(student).setEmail((String) any());
        doNothing().when(student).setFirstName((String) any());
        doNothing().when(student).setId((Integer) any());
        doNothing().when(student).setLastName((String) any());
        doNothing().when(student).setEntry((Entry) any());
        doNothing().when(student).setStudentLessons((List<StudentLesson>) any());
        doNothing().when(student).setTrack((Track) any());
        student.setEmail("jane.doe@example.org");
        student.setEntry(entry);
        student.setFirstName("Jane");
        student.setId(1);
        student.setLastName("Doe");
        student.setStudentLessons(new ArrayList<>());
        student.setTrack(Track.MSD);
        studentController.addNewStudent(student);
    }


    @Test
    @Disabled
    void testDeleteStudent() {

        (new StudentController()).deleteStudent("42");
    }


    @Test
    @Disabled
    void testDeleteStudent2() {

        (new StudentController()).deleteStudent("foo");
    }


    @Test
    @Disabled
    void testShowUpdateStudent() throws NotFoundException {

        StudentController studentController = new StudentController();
        studentController.showUpdateStudent("42", new ConcurrentModel());
    }


    @Test
    @Disabled
    void testShowUpdateStudent2() throws NotFoundException {

        StudentController studentController = new StudentController();
        studentController.showUpdateStudent("foo", new ConcurrentModel());
    }


    @Test
    @Disabled
    void testUpdateStudent() {

        StudentController studentController = new StudentController();
        ConcurrentModel model = new ConcurrentModel();

        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);

        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setEntry(entry);
        student.setFirstName("Jane");
        student.setId(1);
        student.setLastName("Doe");
        student.setStudentLessons(new ArrayList<>());
        student.setTrack(Track.MSD);
        studentController.updateStudent(model, student);
    }


    @Test
    @Disabled
    void testUpdateStudent2() {

        StudentController studentController = new StudentController();
        ConcurrentModel model = new ConcurrentModel();

        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);
        Student student = mock(Student.class);
        doNothing().when(student).setEmail((String) any());
        doNothing().when(student).setFirstName((String) any());
        doNothing().when(student).setId((Integer) any());
        doNothing().when(student).setLastName((String) any());
        doNothing().when(student).setEntry((Entry) any());
        doNothing().when(student).setStudentLessons((List<StudentLesson>) any());
        doNothing().when(student).setTrack((Track) any());
        student.setEmail("jane.doe@example.org");
        student.setEntry(entry);
        student.setFirstName("Jane");
        student.setId(1);
        student.setLastName("Doe");
        student.setStudentLessons(new ArrayList<>());
        student.setTrack(Track.MSD);
        studentController.updateStudent(model, student);
    }
}

