package edu.mum.se.mumsched.service.Impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.mum.se.mumsched.dao.LessonDao;
import edu.mum.se.mumsched.domain.Block;
import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.domain.Faculty;
import edu.mum.se.mumsched.domain.Lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LessonServiceImpl.class})
@ExtendWith(SpringExtension.class)
class LessonServiceImplTest {
    @MockBean
    private LessonDao lessonDao;

    @Autowired
    private LessonServiceImpl lessonServiceImpl;



    @Test
    void testGetLessonsByBlockId() {
        ArrayList<Lesson> lessonList = new ArrayList<>();
        when(lessonDao.findByBlockId((Integer) any())).thenReturn(lessonList);
        List<Lesson> actualLessonsByBlockId = lessonServiceImpl.getLessonsByBlockId("42");
        assertSame(lessonList, actualLessonsByBlockId);
        assertTrue(actualLessonsByBlockId.isEmpty());
        verify(lessonDao).findByBlockId((Integer) any());
    }


    @Test
    void testGetLessonsByBlockId2() {

        when(lessonDao.findByBlockId((Integer) any())).thenReturn(new ArrayList<>());
        lessonServiceImpl.getLessonsByBlockId("Block Id");
    }

    @Test
    void testGetLessonByFacultyId() {
        ArrayList<Lesson> lessonList = new ArrayList<>();
        when(lessonDao.findByFacultyId((Integer) any())).thenReturn(lessonList);
        List<Lesson> actualLessonByFacultyId = lessonServiceImpl.getLessonByFacultyId(123);
        assertSame(lessonList, actualLessonByFacultyId);
        assertTrue(actualLessonByFacultyId.isEmpty());
        verify(lessonDao).findByFacultyId((Integer) any());
    }


    @Test
    void testCreateLesson() {
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
        when(lessonDao.save((Lesson) any())).thenReturn(lesson);
        Block block = new Block();
        Course course = new Course();

        Faculty faculty1 = new Faculty();
        faculty1.setEmail("jane.doe@example.org");
        faculty1.setFirstName("Jane");
        faculty1.setId(1);
        faculty1.setLastName("Doe");
        faculty1.setLessons(new ArrayList<>());
        lessonServiceImpl.createLesson(block, course, faculty1);
        verify(lessonDao).save((Lesson) any());
    }


    @Test
    void testGetLessonById() {
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
        Optional<Lesson> ofResult = Optional.of(lesson);
        when(lessonDao.findById((Integer) any())).thenReturn(ofResult);
        assertSame(lesson, lessonServiceImpl.getLessonById(123));
        verify(lessonDao).findById((Integer) any());
    }


    @Test
    void testGetLessonById2() {

        when(lessonDao.findById((Integer) any())).thenReturn(Optional.empty());
        lessonServiceImpl.getLessonById(123);
    }
}

