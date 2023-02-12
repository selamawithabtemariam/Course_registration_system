package edu.mum.se.mumsched.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import edu.mum.se.mumsched.domain.Faculty;
import edu.mum.se.mumsched.domain.Lesson;
import edu.mum.se.mumsched.service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class FacultyControllerTest {


    @Test
    void testShowFacultyPage() {

        FacultyController facultyController = new FacultyController();
        facultyController.showFacultyPage(new ConcurrentModel());
    }


    @Test
    void testShowNewFacultyForm() {

        FacultyController facultyController = new FacultyController();
        assertEquals("faculty-create", facultyController.showNewFacultyForm(new ConcurrentModel()));
    }


    @Test
    void testAddNewFaculty() {

        FacultyController facultyController = new FacultyController();

        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        facultyController.addNewFaculty(faculty);
    }


    @Test
    void testAddNewFaculty2() {

        FacultyController facultyController = new FacultyController();
        Faculty faculty = mock(Faculty.class);
        doNothing().when(faculty).setLessons((List<Lesson>) any());
        doNothing().when(faculty).setEmail((String) any());
        doNothing().when(faculty).setFirstName((String) any());
        doNothing().when(faculty).setId((Integer) any());
        doNothing().when(faculty).setLastName((String) any());
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        facultyController.addNewFaculty(faculty);
    }


    @Test
    void testDeleteFaculty() {

        (new FacultyController()).deleteFaculty("42");
    }


    @Test
    void testDeleteFaculty2() {

        (new FacultyController()).deleteFaculty("foo");
    }


    @Test
    void testShowUpdateFaculty() throws NotFoundException {

        FacultyController facultyController = new FacultyController();
        facultyController.showUpdateFaculty("42", new ConcurrentModel());
    }


    @Test
    void testShowUpdateFaculty2() throws NotFoundException {

        FacultyController facultyController = new FacultyController();
        facultyController.showUpdateFaculty("foo", new ConcurrentModel());
    }


    @Test
    void testUpdateFaculty() {

        FacultyController facultyController = new FacultyController();
        ConcurrentModel model = new ConcurrentModel();

        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        facultyController.updateFaculty(model, faculty);
    }


    @Test
    void testUpdateFaculty2() {

        FacultyController facultyController = new FacultyController();
        ConcurrentModel model = new ConcurrentModel();
        Faculty faculty = mock(Faculty.class);
        doNothing().when(faculty).setLessons((List<Lesson>) any());
        doNothing().when(faculty).setEmail((String) any());
        doNothing().when(faculty).setFirstName((String) any());
        doNothing().when(faculty).setId((Integer) any());
        doNothing().when(faculty).setLastName((String) any());
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        facultyController.updateFaculty(model, faculty);
    }
}

