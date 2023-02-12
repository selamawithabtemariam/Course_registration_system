package edu.mum.se.mumsched.service.Impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.mum.se.mumsched.dao.CourseDao;
import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CourseServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CourseServiceImplTest {
    @MockBean
    private CourseDao courseDao;

    @Autowired
    private CourseServiceImpl courseServiceImpl;


    @Test
    void testAddCourse() {
        when(courseDao.save((Course) any())).thenReturn(new Course());
        courseServiceImpl.addCourse(new Course());
        verify(courseDao).save((Course) any());
        assertTrue(courseServiceImpl.getAllCourses().isEmpty());
    }


    @Test
    void testAddCourse2() {
        when(courseDao.save((Course) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> courseServiceImpl.addCourse(new Course()));
        verify(courseDao).save((Course) any());
    }



    @Test
    void testGetCourse() throws NotFoundException {
        Course course = new Course();
        when(courseDao.findById((Integer) any())).thenReturn(Optional.of(course));
        assertSame(course, courseServiceImpl.getCourse(1));
        verify(courseDao).findById((Integer) any());
    }



    @Test
    void testGetCourse2() throws NotFoundException {
        when(courseDao.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> courseServiceImpl.getCourse(1));
        verify(courseDao).findById((Integer) any());
    }



    @Test
    void testGetCourse3() throws NotFoundException {
        when(courseDao.findById((Integer) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> courseServiceImpl.getCourse(1));
        verify(courseDao).findById((Integer) any());
    }



    @Test
    void testGetAllCourses() {
        ArrayList<Course> courseList = new ArrayList<>();
        when(courseDao.findAllByOrderByNameAsc()).thenReturn(courseList);
        List<Course> actualAllCourses = courseServiceImpl.getAllCourses();
        assertSame(courseList, actualAllCourses);
        assertTrue(actualAllCourses.isEmpty());
        verify(courseDao).findAllByOrderByNameAsc();
    }



    @Test
    void testGetAllCourses2() {
        when(courseDao.findAllByOrderByNameAsc()).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> courseServiceImpl.getAllCourses());
        verify(courseDao).findAllByOrderByNameAsc();
    }



    @Test
    void testDeleteCourse() throws NotFoundException {
        doNothing().when(courseDao).deleteById((Integer) any());
        courseServiceImpl.deleteCourse("42");
        verify(courseDao).deleteById((Integer) any());
        assertTrue(courseServiceImpl.getAllCourses().isEmpty());
    }



    @Test
    void testDeleteCourse2() throws NotFoundException {
        doNothing().when(courseDao).deleteById((Integer) any());
        assertThrows(NotFoundException.class, () -> courseServiceImpl.deleteCourse("Course Id"));
    }



    @Test
    void testDeleteCourse3() throws NotFoundException {
        doThrow(new NotFoundException("An error occurred")).when(courseDao).deleteById((Integer) any());
        assertThrows(NotFoundException.class, () -> courseServiceImpl.deleteCourse("42"));
        verify(courseDao).deleteById((Integer) any());
    }



    @Test
    void testDeleteCourse4() throws NotFoundException {
        doThrow(new NumberFormatException()).when(courseDao).deleteById((Integer) any());
        assertThrows(NotFoundException.class, () -> courseServiceImpl.deleteCourse("42"));
        verify(courseDao).deleteById((Integer) any());
    }



    @Test
    void testEditCourse() {
        when(courseDao.save((Course) any())).thenReturn(new Course());
        courseServiceImpl.editCourse(new Course());
        verify(courseDao).save((Course) any());
        assertTrue(courseServiceImpl.getAllCourses().isEmpty());
    }



    @Test
    void testEditCourse2() {
        when(courseDao.save((Course) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> courseServiceImpl.editCourse(new Course()));
        verify(courseDao).save((Course) any());
    }
}

