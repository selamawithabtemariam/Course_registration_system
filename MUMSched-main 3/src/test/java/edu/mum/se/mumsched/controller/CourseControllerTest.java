package edu.mum.se.mumsched.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.service.exception.NotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class CourseControllerTest {

    @Test
    void testShowCoursePage() {

        CourseController courseController = new CourseController();
        courseController.showCoursePage(new ConcurrentModel());
    }


    @Test
    void testShowNewCourseForm() {

        CourseController courseController = new CourseController();
        assertEquals("course-create", courseController.showNewCourseForm(new ConcurrentModel()));
    }


    @Test
    void testAddNewCourse() {

        CourseController courseController = new CourseController();
        courseController.addNewCourse(new Course());
    }


    @Test
    void testAddNewCourse2() {

        (new CourseController()).addNewCourse(mock(Course.class));
    }


    @Test
    void testDeleteCourse() throws NotFoundException {

        (new CourseController()).deleteCourse("42");
    }


    @Test
    void testShowUpdateCourse() throws NotFoundException {

        CourseController courseController = new CourseController();
        courseController.showUpdateCourse("42", new ConcurrentModel());
    }


    @Test
    void testShowUpdateCourse2() throws NotFoundException {

        CourseController courseController = new CourseController();
        courseController.showUpdateCourse("foo", new ConcurrentModel());
    }


    @Test
    void testUpdateCourse() {

        CourseController courseController = new CourseController();
        ConcurrentModel model = new ConcurrentModel();
        courseController.updateCourse(model, new Course());
    }


    @Test
    void testUpdateCourse2() {

        CourseController courseController = new CourseController();
        courseController.updateCourse(new ConcurrentModel(), mock(Course.class));
    }
}

