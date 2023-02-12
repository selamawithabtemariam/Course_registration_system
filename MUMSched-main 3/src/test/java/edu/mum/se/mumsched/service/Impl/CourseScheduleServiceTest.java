package edu.mum.se.mumsched.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.mum.se.mumsched.dao.CourseScheduleRepository;
import edu.mum.se.mumsched.domain.Block;
import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.domain.CourseSchedule;

import java.util.ArrayList;

import java.util.Collection;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CourseScheduleService.class})
@ExtendWith(SpringExtension.class)
class CourseScheduleServiceTest {
    @MockBean
    private CourseScheduleRepository courseScheduleRepository;

    @Autowired
    private CourseScheduleService courseScheduleService;



    @Test
    void testGetCourseSchedules() {
        ArrayList<CourseSchedule> courseScheduleList = new ArrayList<>();
        when(courseScheduleRepository.findAll()).thenReturn(courseScheduleList);
        Iterable<CourseSchedule> actualCourseSchedules = courseScheduleService.getCourseSchedules();
        assertSame(courseScheduleList, actualCourseSchedules);
        assertTrue(((Collection<CourseSchedule>) actualCourseSchedules).isEmpty());
        verify(courseScheduleRepository).findAll();
    }



    @Test
    void testGetCourseSchedule() {
        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setBlock(new Block());
        courseSchedule.setCourse(new Course());
        courseSchedule.setId(123L);
        when(courseScheduleRepository.findCourseScheduleById(anyLong())).thenReturn(courseSchedule);
        assertSame(courseSchedule, courseScheduleService.getCourseSchedule(123L));
        verify(courseScheduleRepository).findCourseScheduleById(anyLong());
    }



    @Test
    void testCreate() {

        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setBlock(new Block());
        courseSchedule.setCourse(new Course());
        courseSchedule.setId(123L);
        when(courseScheduleRepository.save((CourseSchedule) any())).thenReturn(courseSchedule);

        CourseSchedule courseSchedule1 = new CourseSchedule();
        courseSchedule1.setBlock(new Block());
        courseSchedule1.setCourse(new Course());
        courseSchedule1.setId(123L);
        courseScheduleService.Create(courseSchedule1);
    }



    @Test
    void testCreate2() {

        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setBlock(new Block());
        courseSchedule.setCourse(new Course());
        courseSchedule.setId(123L);
        when(courseScheduleRepository.save((CourseSchedule) any())).thenReturn(courseSchedule);
        CourseSchedule courseSchedule1 = mock(CourseSchedule.class);
        when(courseSchedule1.getBlock()).thenReturn(new Block());
        when(courseSchedule1.getCourse()).thenReturn(new Course());
        doNothing().when(courseSchedule1).setBlock((Block) any());
        doNothing().when(courseSchedule1).setCourse((Course) any());
        doNothing().when(courseSchedule1).setId((Long) any());
        courseSchedule1.setBlock(new Block());
        courseSchedule1.setCourse(new Course());
        courseSchedule1.setId(123L);
        courseScheduleService.Create(courseSchedule1);
    }


    @Test
    void testCreate3() {
        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setBlock(new Block());
        courseSchedule.setCourse(new Course());
        courseSchedule.setId(123L);
        when(courseScheduleRepository.save((CourseSchedule) any())).thenReturn(courseSchedule);
        CourseSchedule courseSchedule1 = mock(CourseSchedule.class);
        when(courseSchedule1.getBlock()).thenReturn(null);
        when(courseSchedule1.getCourse()).thenReturn(new Course());
        doNothing().when(courseSchedule1).setBlock((Block) any());
        doNothing().when(courseSchedule1).setCourse((Course) any());
        doNothing().when(courseSchedule1).setId((Long) any());
        courseSchedule1.setBlock(new Block());
        courseSchedule1.setCourse(new Course());
        courseSchedule1.setId(123L);
        ResponseEntity actualCreateResult = courseScheduleService.Create(courseSchedule1);
        assertTrue(actualCreateResult.hasBody());
        assertEquals(201, actualCreateResult.getStatusCodeValue());
        assertTrue(actualCreateResult.getHeaders().isEmpty());
        verify(courseScheduleRepository).save((CourseSchedule) any());
        verify(courseSchedule1).getBlock();
        verify(courseSchedule1).getCourse();
        verify(courseSchedule1).setBlock((Block) any());
        verify(courseSchedule1).setCourse((Course) any());
        verify(courseSchedule1).setId((Long) any());
    }



    @Test
    void testUpdate() {

        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setBlock(new Block());
        courseSchedule.setCourse(new Course());
        courseSchedule.setId(123L);
        when(courseScheduleRepository.save((CourseSchedule) any())).thenReturn(courseSchedule);

        CourseSchedule courseSchedule1 = new CourseSchedule();
        courseSchedule1.setBlock(new Block());
        courseSchedule1.setCourse(new Course());
        courseSchedule1.setId(123L);
        courseScheduleService.Update(courseSchedule1);
    }


    @Test
    void testUpdate2() {
        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setBlock(new Block());
        courseSchedule.setCourse(new Course());
        courseSchedule.setId(123L);
        when(courseScheduleRepository.save((CourseSchedule) any())).thenReturn(courseSchedule);
        CourseSchedule courseSchedule1 = mock(CourseSchedule.class);
        doNothing().when(courseSchedule1).setBlock((Block) any());
        doNothing().when(courseSchedule1).setCourse((Course) any());
        doNothing().when(courseSchedule1).setId((Long) any());
        courseSchedule1.setBlock(new Block());
        courseSchedule1.setCourse(new Course());
        courseSchedule1.setId(123L);
        ResponseEntity actualUpdateResult = courseScheduleService.Update(courseSchedule1);
        assertTrue(actualUpdateResult.hasBody());
        assertEquals(201, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        verify(courseScheduleRepository).save((CourseSchedule) any());
        verify(courseSchedule1).setBlock((Block) any());
        verify(courseSchedule1).setCourse((Course) any());
        verify(courseSchedule1).setId((Long) any());
    }


    @Test
    void testDelete() {
        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setBlock(new Block());
        courseSchedule.setCourse(new Course());
        courseSchedule.setId(123L);
        when(courseScheduleRepository.findCourseScheduleById(anyLong())).thenReturn(courseSchedule);
        doNothing().when(courseScheduleRepository).delete((CourseSchedule) any());
        courseScheduleService.delete(123L);
        verify(courseScheduleRepository).findCourseScheduleById(anyLong());
        verify(courseScheduleRepository).delete((CourseSchedule) any());
        assertTrue(((Collection<CourseSchedule>) courseScheduleService.getCourseSchedules()).isEmpty());
    }
}

