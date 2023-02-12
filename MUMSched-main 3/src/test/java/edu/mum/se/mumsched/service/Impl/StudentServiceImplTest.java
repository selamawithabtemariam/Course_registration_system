package edu.mum.se.mumsched.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.mum.se.mumsched.dao.StudentDao;
import edu.mum.se.mumsched.domain.Entry;
import edu.mum.se.mumsched.domain.Student;
import edu.mum.se.mumsched.domain.StudentLesson;
import edu.mum.se.mumsched.domain.Track;
import edu.mum.se.mumsched.service.EntryService;
import edu.mum.se.mumsched.service.UserService;
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

@ContextConfiguration(classes = {StudentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StudentServiceImplTest {
    @MockBean
    private EntryService entryService;

    @MockBean
    private StudentDao studentDao;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @MockBean
    private UserService userService;


    @Test
    void testAddStudent() throws NotFoundException {
        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);
        when(entryService.getEntry((Integer) any())).thenReturn(entry);

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
        when(studentDao.save((Student) any())).thenReturn(student);
        when(userService.createUser((String) any(), (String) any())).thenReturn(1);

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
        studentServiceImpl.addStudent(student1);
        verify(entryService).getEntry((Integer) any());
        verify(studentDao).save((Student) any());
        verify(userService).createUser((String) any(), (String) any());
        assertEquals(1, student1.getId().intValue());
        assertSame(entry, student1.getEntry());
    }


    @Test
    void testAddStudent2() throws NotFoundException {
        Entry entry = new Entry();
        entry.setId(1);
        entry.setMonth(1);
        entry.setYear(1);
        when(entryService.getEntry((Integer) any())).thenReturn(entry);

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
        when(studentDao.save((Student) any())).thenReturn(student);
        when(userService.createUser((String) any(), (String) any()))
                .thenThrow(new NotFoundException("An error occurred"));

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
        assertThrows(NotFoundException.class, () -> studentServiceImpl.addStudent(student1));
        verify(userService).createUser((String) any(), (String) any());
    }


    @Test
    void testGetStudent() throws NotFoundException {
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
        Optional<Student> ofResult = Optional.of(student);
        when(studentDao.findById((Integer) any())).thenReturn(ofResult);
        assertSame(student, studentServiceImpl.getStudent(1));
        verify(studentDao).findById((Integer) any());
    }


    @Test
    void testGetStudent2() throws NotFoundException {
        when(studentDao.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> studentServiceImpl.getStudent(1));
        verify(studentDao).findById((Integer) any());
    }


    @Test
    void testGetStudent3() throws NotFoundException {
        when(studentDao.findById((Integer) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> studentServiceImpl.getStudent(1));
        verify(studentDao).findById((Integer) any());
    }


    @Test
    void testGetAllStudents() {
        ArrayList<Student> studentList = new ArrayList<>();
        when(studentDao.findAll()).thenReturn(studentList);
        List<Student> actualAllStudents = studentServiceImpl.getAllStudents();
        assertSame(studentList, actualAllStudents);
        assertTrue(actualAllStudents.isEmpty());
        verify(studentDao).findAll();
    }


    @Test
    void testGetAllStudents2() {
        when(studentDao.findAll()).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> studentServiceImpl.getAllStudents());
        verify(studentDao).findAll();
    }


    @Test
    void testDeleteStudent() {
        doNothing().when(studentDao).deleteById((Integer) any());
        studentServiceImpl.deleteStudent(123);
        verify(studentDao).deleteById((Integer) any());
        assertTrue(studentServiceImpl.getAllStudents().isEmpty());
    }


    @Test
    void testDeleteStudent2() {
        doThrow(new NotFoundException("An error occurred")).when(studentDao).deleteById((Integer) any());
        assertThrows(NotFoundException.class, () -> studentServiceImpl.deleteStudent(123));
        verify(studentDao).deleteById((Integer) any());
    }


    @Test
    void testUpdateStudent() {
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
        ArrayList<StudentLesson> studentLessonList = new ArrayList<>();
        student.setStudentLessons(studentLessonList);
        student.setTrack(Track.MSD);
        when(studentDao.save((Student) any())).thenReturn(student);
        doNothing().when(userService).updateUser((Integer) any(), (String) any());

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
        studentServiceImpl.updateStudent(student1);
        verify(studentDao).save((Student) any());
        verify(userService).updateUser((Integer) any(), (String) any());
        assertEquals("jane.doe@example.org", student1.getEmail());
        assertEquals(Track.MSD, student1.getTrack());
        assertEquals(studentLessonList, student1.getStudentLessons());
        assertEquals("Doe", student1.getLastName());
        assertEquals(1, student1.getId().intValue());
        assertEquals("Jane", student1.getFirstName());
        assertSame(entry1, student1.getEntry());
        assertTrue(studentServiceImpl.getAllStudents().isEmpty());
    }


    @Test
    void testUpdateStudent2() {
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
        when(studentDao.save((Student) any())).thenReturn(student);
        doThrow(new NotFoundException("An error occurred")).when(userService).updateUser((Integer) any(), (String) any());

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
        assertThrows(NotFoundException.class, () -> studentServiceImpl.updateStudent(student1));
        verify(userService).updateUser((Integer) any(), (String) any());
    }
}

