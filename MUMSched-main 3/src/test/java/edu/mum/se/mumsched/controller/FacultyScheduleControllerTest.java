package edu.mum.se.mumsched.controller;

import static org.mockito.Mockito.mock;

import edu.mum.se.mumsched.domain.Course;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class FacultyScheduleControllerTest {


    @Test
    void testShowFacultySchedule() {

        FacultyScheduleController facultyScheduleController = new FacultyScheduleController();
        facultyScheduleController.showFacultySchedule(new ConcurrentModel());
    }


    @Test
    void testShowFacultyAddScheduleForm() {

        FacultyScheduleController facultyScheduleController = new FacultyScheduleController();
        facultyScheduleController.showFacultyAddScheduleForm(new ConcurrentModel(), "42");
    }


    @Test
    void testShowFacultyAddScheduleForm2() {

        FacultyScheduleController facultyScheduleController = new FacultyScheduleController();
        facultyScheduleController.showFacultyAddScheduleForm(new ConcurrentModel(), "foo");
    }


    @Test
    void testCreateLesson() {

        FacultyScheduleController facultyScheduleController = new FacultyScheduleController();
        facultyScheduleController.createLesson("42", new Course());
    }


    @Test
    void testCreateLesson2() {

        (new FacultyScheduleController()).createLesson("42", mock(Course.class));
    }


    @Test
    void testViewStudentsForSchedule() {

        FacultyScheduleController facultyScheduleController = new FacultyScheduleController();
        facultyScheduleController.viewStudentsForSchedule(new ConcurrentModel(), "42");
    }


    @Test
    void testViewStudentsForSchedule2() {

        FacultyScheduleController facultyScheduleController = new FacultyScheduleController();
        facultyScheduleController.viewStudentsForSchedule(new ConcurrentModel(), "foo");
    }
}

