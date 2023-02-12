package edu.mum.se.mumsched.service.Impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.mum.se.mumsched.dao.StudentLessonDao;
import edu.mum.se.mumsched.domain.Block;
import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.domain.Entry;
import edu.mum.se.mumsched.domain.Faculty;
import edu.mum.se.mumsched.domain.Lesson;
import edu.mum.se.mumsched.domain.Student;
import edu.mum.se.mumsched.domain.StudentLesson;
import edu.mum.se.mumsched.domain.Track;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StudentLessonServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StudentLessonServiceImplTest {
    @MockBean
    private StudentLessonDao studentLessonDao;

    @Autowired
    private StudentLessonServiceImpl studentLessonServiceImpl;


    @Test
    void testCreateStudentLesson() {
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

        StudentLesson studentLesson = new StudentLesson();
        studentLesson.setId(1);
        studentLesson.setLesson(lesson);
        studentLesson.setStudent(student);
        when(studentLessonDao.save((StudentLesson) any())).thenReturn(studentLesson);

        Faculty faculty1 = new Faculty();
        faculty1.setEmail("jane.doe@example.org");
        faculty1.setFirstName("Jane");
        faculty1.setId(1);
        faculty1.setLastName("Doe");
        faculty1.setLessons(new ArrayList<>());

        Lesson lesson1 = new Lesson();
        lesson1.setBlock(new Block());
        lesson1.setCourse(new Course());
        lesson1.setFaculty(faculty1);
        lesson1.setId(1);
        lesson1.setStudentLessons(new ArrayList<>());

        Entry entry1 = new Entry();
        entry1.setId(1);
        entry1.setMonth(1);
        entry1.setYear(1);

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setEntry(entry1);
        student1.setFirstName("Jane");
        student1.setId(1);
        student1.setLastName("Doe");
        student1.setStudentLessons(new ArrayList<>());
        student1.setTrack(Track.MSD);
        studentLessonServiceImpl.createStudentLesson(lesson1, student1);
        verify(studentLessonDao).save((StudentLesson) any());
    }
}

