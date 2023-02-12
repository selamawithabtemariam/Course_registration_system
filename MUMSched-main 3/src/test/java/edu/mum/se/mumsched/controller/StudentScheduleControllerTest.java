package edu.mum.se.mumsched.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import edu.mum.se.mumsched.domain.Block;
import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.domain.Faculty;
import edu.mum.se.mumsched.domain.Lesson;
import edu.mum.se.mumsched.domain.StudentLesson;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class StudentScheduleControllerTest {


    @Test
    @Disabled
    void testShowStudentSchedule() {

        StudentScheduleController studentScheduleController = new StudentScheduleController();
        studentScheduleController.showStudentSchedule(new ConcurrentModel());
    }


    @Test
    @Disabled
    void testShowStudentAddScheduleForm() {

        StudentScheduleController studentScheduleController = new StudentScheduleController();
        studentScheduleController.showStudentAddScheduleForm(new ConcurrentModel(), "42");
    }


    @Test
    @Disabled
    void testShowStudentAddScheduleForm2() {

        StudentScheduleController studentScheduleController = new StudentScheduleController();
        studentScheduleController.showStudentAddScheduleForm(new ConcurrentModel(), "foo");
    }


    @Test
    @Disabled
    void testCreateLesson() {

        StudentScheduleController studentScheduleController = new StudentScheduleController();

        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());

        Lesson lesson = new Lesson();
        lesson.setBlock(new Block());
        lesson.setCourse(new Course());
        lesson.setFaculty(faculty);
        lesson.setId(1);
        lesson.setStudentLessons(new ArrayList<>());
        studentScheduleController.createLesson(lesson);
    }


    @Test
    @Disabled
    void testCreateLesson2() {

        StudentScheduleController studentScheduleController = new StudentScheduleController();

        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        Lesson lesson = mock(Lesson.class);
        doNothing().when(lesson).setBlock((Block) any());
        doNothing().when(lesson).setCourse((Course) any());
        doNothing().when(lesson).setFaculty((Faculty) any());
        doNothing().when(lesson).setId((Integer) any());
        doNothing().when(lesson).setStudentLessons((List<StudentLesson>) any());
        lesson.setBlock(new Block());
        lesson.setCourse(new Course());
        lesson.setFaculty(faculty);
        lesson.setId(1);
        lesson.setStudentLessons(new ArrayList<>());
        studentScheduleController.createLesson(lesson);
    }
}

