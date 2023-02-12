package edu.mum.se.mumsched.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.mum.se.mumsched.component.SessionManager;
import edu.mum.se.mumsched.domain.Block;
import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.domain.CustomUser;
import edu.mum.se.mumsched.domain.Entry;
import edu.mum.se.mumsched.domain.Faculty;
import edu.mum.se.mumsched.domain.Lesson;
import edu.mum.se.mumsched.domain.Student;
import edu.mum.se.mumsched.domain.StudentLesson;
import edu.mum.se.mumsched.domain.StudentSchedule;
import edu.mum.se.mumsched.domain.Track;
import edu.mum.se.mumsched.service.BlockService;
import edu.mum.se.mumsched.service.LessonService;
import edu.mum.se.mumsched.service.StudentLessonService;
import edu.mum.se.mumsched.service.StudentService;
import edu.mum.se.mumsched.service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StudentScheduleServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StudentScheduleServiceImplTest {
    @MockBean
    private BlockService blockService;

    @MockBean
    private LessonService lessonService;

    @MockBean
    private SessionManager sessionManager;

    @MockBean
    private StudentLessonService studentLessonService;

    @Autowired
    private StudentScheduleServiceImpl studentScheduleServiceImpl;

    @MockBean
    private StudentService studentService;


    @Test
    void testGetSchedules() throws NotFoundException {
        when(blockService.getAllBlocks()).thenReturn(new ArrayList<>());
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));

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
        when(studentService.getStudent((Integer) any())).thenReturn(student);
        assertTrue(studentScheduleServiceImpl.getSchedules().isEmpty());
        verify(blockService).getAllBlocks();
        verify(sessionManager).getUser();
        verify(studentService).getStudent((Integer) any());
    }


    @Test
    void testGetSchedules2() throws NotFoundException {
        ArrayList<Block> blockList = new ArrayList<>();
        Block block = new Block();
        blockList.add(block);
        when(blockService.getAllBlocks()).thenReturn(blockList);
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));

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
        when(studentService.getStudent((Integer) any())).thenReturn(student);
        List<StudentSchedule> actualSchedules = studentScheduleServiceImpl.getSchedules();
        assertEquals(1, actualSchedules.size());
        assertSame(block, actualSchedules.get(0).getBlock());
        verify(blockService).getAllBlocks();
        verify(sessionManager).getUser();
        verify(studentService).getStudent((Integer) any());
    }

    /**
     * Method under test: {@link StudentScheduleServiceImpl#getSchedules()}
     */
    @Test
    void testGetSchedules3() throws NotFoundException {

        when(blockService.getAllBlocks()).thenReturn(new ArrayList<>());
        when(sessionManager.getUser()).thenReturn(null);

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
        when(studentService.getStudent((Integer) any())).thenReturn(student);
        studentScheduleServiceImpl.getSchedules();
    }


    @Test
    void testGetSchedules4() throws NotFoundException {
        when(blockService.getAllBlocks()).thenReturn(new ArrayList<>());
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));

        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);

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

        Entry entry1 = new Entry();
        entry1.setId(1);
        entry1.setMonth(1);
        entry1.setYear(1);

        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setEntry(entry1);
        student.setFirstName("Jane");
        student.setId(1);
        student.setLastName("Doe");
        student.setStudentLessons(new ArrayList<>());
        student.setTrack(Track.MSD);

        StudentLesson studentLesson = new StudentLesson();
        studentLesson.setId(1);
        studentLesson.setLesson(lesson);
        studentLesson.setStudent(student);

        ArrayList<StudentLesson> studentLessonList = new ArrayList<>();
        studentLessonList.add(studentLesson);

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setEntry(entry);
        student1.setFirstName("Jane");
        student1.setId(1);
        student1.setLastName("Doe");
        student1.setStudentLessons(studentLessonList);
        student1.setTrack(Track.MSD);
        when(studentService.getStudent((Integer) any())).thenReturn(student1);
        assertTrue(studentScheduleServiceImpl.getSchedules().isEmpty());
        verify(blockService).getAllBlocks();
        verify(sessionManager).getUser();
        verify(studentService).getStudent((Integer) any());
    }


    @Test
    void testGetSchedules5() throws NotFoundException {

        when(blockService.getAllBlocks()).thenReturn(new ArrayList<>());
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));

        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);

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

        Entry entry1 = new Entry();
        entry1.setId(1);
        entry1.setMonth(1);
        entry1.setYear(1);

        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setEntry(entry1);
        student.setFirstName("Jane");
        student.setId(1);
        student.setLastName("Doe");
        student.setStudentLessons(new ArrayList<>());
        student.setTrack(Track.MSD);

        StudentLesson studentLesson = new StudentLesson();
        studentLesson.setId(1);
        studentLesson.setLesson(lesson);
        studentLesson.setStudent(student);

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

        Entry entry2 = new Entry();
        entry2.setId(1);
        entry2.setMonth(1);
        entry2.setYear(1);

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setEntry(entry2);
        student1.setFirstName("Jane");
        student1.setId(1);
        student1.setLastName("Doe");
        student1.setStudentLessons(new ArrayList<>());
        student1.setTrack(Track.MSD);

        StudentLesson studentLesson1 = new StudentLesson();
        studentLesson1.setId(1);
        studentLesson1.setLesson(lesson1);
        studentLesson1.setStudent(student1);

        ArrayList<StudentLesson> studentLessonList = new ArrayList<>();
        studentLessonList.add(studentLesson1);
        studentLessonList.add(studentLesson);

        Student student2 = new Student();
        student2.setEmail("jane.doe@example.org");
        student2.setEntry(entry);
        student2.setFirstName("Jane");
        student2.setId(1);
        student2.setLastName("Doe");
        student2.setStudentLessons(studentLessonList);
        student2.setTrack(Track.MSD);
        when(studentService.getStudent((Integer) any())).thenReturn(student2);
        studentScheduleServiceImpl.getSchedules();
    }



    @Test
    void testGetSchedules6() throws NotFoundException {
        ArrayList<Block> blockList = new ArrayList<>();
        Block block = new Block();
        blockList.add(block);
        when(blockService.getAllBlocks()).thenReturn(blockList);
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));

        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);

        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());

        Lesson lesson = new Lesson();
        lesson.setBlock(new Block());
        Course course = new Course();
        lesson.setCourse(course);
        lesson.setFaculty(faculty);
        lesson.setId(1);
        lesson.setStudentLessons(new ArrayList<>());

        Entry entry1 = new Entry();
        entry1.setId(1);
        entry1.setMonth(1);
        entry1.setYear(1);

        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setEntry(entry1);
        student.setFirstName("Jane");
        student.setId(1);
        student.setLastName("Doe");
        student.setStudentLessons(new ArrayList<>());
        student.setTrack(Track.MSD);

        StudentLesson studentLesson = new StudentLesson();
        studentLesson.setId(1);
        studentLesson.setLesson(lesson);
        studentLesson.setStudent(student);

        ArrayList<StudentLesson> studentLessonList = new ArrayList<>();
        studentLessonList.add(studentLesson);

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setEntry(entry);
        student1.setFirstName("Jane");
        student1.setId(1);
        student1.setLastName("Doe");
        student1.setStudentLessons(studentLessonList);
        student1.setTrack(Track.MSD);
        when(studentService.getStudent((Integer) any())).thenReturn(student1);
        List<StudentSchedule> actualSchedules = studentScheduleServiceImpl.getSchedules();
        assertEquals(1, actualSchedules.size());
        StudentSchedule getResult = actualSchedules.get(0);
        assertSame(block, getResult.getBlock());
        assertEquals(1, getResult.getLessonId().intValue());
        assertSame(course, getResult.getCourse());
        assertSame(faculty, getResult.getFaculty());
        verify(blockService).getAllBlocks();
        verify(sessionManager).getUser();
        verify(studentService).getStudent((Integer) any());
    }


    @Test
    void testAssignToLesson() throws NotFoundException {
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
        when(lessonService.getLessonById((Integer) any())).thenReturn(lesson);
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        doNothing().when(studentLessonService).createStudentLesson((Lesson) any(), (Student) any());

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
        when(studentService.getStudent((Integer) any())).thenReturn(student);

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
        studentScheduleServiceImpl.assignToLesson(lesson1);
        verify(lessonService).getLessonById((Integer) any());
        verify(sessionManager).getUser();
        verify(studentLessonService).createStudentLesson((Lesson) any(), (Student) any());
        verify(studentService).getStudent((Integer) any());
    }


    @Test
    void testAssignToLesson2() throws NotFoundException {

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
        when(lessonService.getLessonById((Integer) any())).thenReturn(lesson);
        when(sessionManager.getUser()).thenReturn(null);
        doNothing().when(studentLessonService).createStudentLesson((Lesson) any(), (Student) any());

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
        when(studentService.getStudent((Integer) any())).thenReturn(student);

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
        studentScheduleServiceImpl.assignToLesson(lesson1);
    }
}

