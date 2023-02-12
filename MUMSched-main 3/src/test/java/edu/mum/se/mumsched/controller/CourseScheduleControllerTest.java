package edu.mum.se.mumsched.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import edu.mum.se.mumsched.domain.Block;
import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.domain.CourseSchedule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CourseScheduleControllerTest {


    @Test
    void testGetList() {

        (new CourseScheduleController()).getList();
    }


    @Test
    void testCreate() {

        (new CourseScheduleController()).create();
    }


    @Test
    void testCreate2() {

        CourseScheduleController courseScheduleController = new CourseScheduleController();

        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setBlock(new Block());
        courseSchedule.setCourse(new Course());
        courseSchedule.setId(123L);
        courseScheduleController.create(courseSchedule);
    }


    @Test
    void testCreate3() {

        CourseScheduleController courseScheduleController = new CourseScheduleController();
        CourseSchedule courseSchedule = mock(CourseSchedule.class);
        doNothing().when(courseSchedule).setBlock((Block) any());
        doNothing().when(courseSchedule).setCourse((Course) any());
        doNothing().when(courseSchedule).setId((Long) any());
        courseSchedule.setBlock(new Block());
        courseSchedule.setCourse(new Course());
        courseSchedule.setId(123L);
        courseScheduleController.create(courseSchedule);
    }


    @Test
    void testEdit() {

        CourseScheduleController courseScheduleController = new CourseScheduleController();

        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setBlock(new Block());
        courseSchedule.setCourse(new Course());
        courseSchedule.setId(123L);
        courseScheduleController.edit(courseSchedule);
    }


    @Test
    void testEdit2() {

        CourseScheduleController courseScheduleController = new CourseScheduleController();
        CourseSchedule courseSchedule = mock(CourseSchedule.class);
        doNothing().when(courseSchedule).setBlock((Block) any());
        doNothing().when(courseSchedule).setCourse((Course) any());
        doNothing().when(courseSchedule).setId((Long) any());
        courseSchedule.setBlock(new Block());
        courseSchedule.setCourse(new Course());
        courseSchedule.setId(123L);
        courseScheduleController.edit(courseSchedule);
    }


    @Test
    void testEdit3() {

        (new CourseScheduleController()).edit(123L);
    }


    @Test
    void testDelete() {

        (new CourseScheduleController()).delete(123L);
    }
}

