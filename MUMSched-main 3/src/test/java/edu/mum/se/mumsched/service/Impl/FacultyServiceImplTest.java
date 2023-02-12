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

import edu.mum.se.mumsched.dao.FacultyDao;
import edu.mum.se.mumsched.domain.Faculty;
import edu.mum.se.mumsched.domain.Lesson;
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

@ContextConfiguration(classes = {FacultyServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FacultyServiceImplTest {
    @MockBean
    private FacultyDao facultyDao;

    @Autowired
    private FacultyServiceImpl facultyServiceImpl;

    @MockBean
    private UserService userService;


    @Test
    void testAddFaculty() {
        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        when(facultyDao.save((Faculty) any())).thenReturn(faculty);
        when(userService.createUser((String) any(), (String) any())).thenReturn(1);

        Faculty faculty1 = new Faculty();
        faculty1.setEmail("jane.doe@example.org");
        faculty1.setFirstName("Jane");
        faculty1.setId(1);
        faculty1.setLastName("Doe");
        faculty1.setLessons(new ArrayList<>());
        facultyServiceImpl.addFaculty(faculty1);
        verify(facultyDao).save((Faculty) any());
        verify(userService).createUser((String) any(), (String) any());
        assertEquals(1, faculty1.getId().intValue());
    }


    @Test
    void testAddFaculty2() {
        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        when(facultyDao.save((Faculty) any())).thenReturn(faculty);
        when(userService.createUser((String) any(), (String) any()))
                .thenThrow(new NotFoundException("An error occurred"));

        Faculty faculty1 = new Faculty();
        faculty1.setEmail("jane.doe@example.org");
        faculty1.setFirstName("Jane");
        faculty1.setId(1);
        faculty1.setLastName("Doe");
        faculty1.setLessons(new ArrayList<>());
        assertThrows(NotFoundException.class, () -> facultyServiceImpl.addFaculty(faculty1));
        verify(userService).createUser((String) any(), (String) any());
    }


    @Test
    void testGetFaculty() {
        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        Optional<Faculty> ofResult = Optional.of(faculty);
        when(facultyDao.findById((Integer) any())).thenReturn(ofResult);
        assertSame(faculty, facultyServiceImpl.getFaculty(1));
        verify(facultyDao).findById((Integer) any());
    }


    @Test
    void testGetFaculty2() {
        when(facultyDao.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> facultyServiceImpl.getFaculty(1));
        verify(facultyDao).findById((Integer) any());
    }


    @Test
    void testGetFaculty3() {
        when(facultyDao.findById((Integer) any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> facultyServiceImpl.getFaculty(1));
        verify(facultyDao).findById((Integer) any());
    }


    @Test
    void testGetAllFaculties() {
        ArrayList<Faculty> facultyList = new ArrayList<>();
        when(facultyDao.findAll()).thenReturn(facultyList);
        List<Faculty> actualAllFaculties = facultyServiceImpl.getAllFaculties();
        assertSame(facultyList, actualAllFaculties);
        assertTrue(actualAllFaculties.isEmpty());
        verify(facultyDao).findAll();
    }


    @Test
    void testGetAllFaculties2() {
        when(facultyDao.findAll()).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> facultyServiceImpl.getAllFaculties());
        verify(facultyDao).findAll();
    }


    @Test
    void testDeleteFaculty() {
        doNothing().when(facultyDao).deleteById((Integer) any());
        facultyServiceImpl.deleteFaculty(123);
        verify(facultyDao).deleteById((Integer) any());
        assertTrue(facultyServiceImpl.getAllFaculties().isEmpty());
    }


    @Test
    void testDeleteFaculty2() {
        doThrow(new NotFoundException("An error occurred")).when(facultyDao).deleteById((Integer) any());
        assertThrows(NotFoundException.class, () -> facultyServiceImpl.deleteFaculty(123));
        verify(facultyDao).deleteById((Integer) any());
    }



    @Test
    void testEditFaculty() {
        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        ArrayList<Lesson> lessonList = new ArrayList<>();
        faculty.setLessons(lessonList);
        when(facultyDao.save((Faculty) any())).thenReturn(faculty);
        doNothing().when(userService).updateUser((Integer) any(), (String) any());

        Faculty faculty1 = new Faculty();
        faculty1.setEmail("jane.doe@example.org");
        faculty1.setFirstName("Jane");
        faculty1.setId(1);
        faculty1.setLastName("Doe");
        faculty1.setLessons(new ArrayList<>());
        facultyServiceImpl.editFaculty(faculty1);
        verify(facultyDao).save((Faculty) any());
        verify(userService).updateUser((Integer) any(), (String) any());
        assertEquals("jane.doe@example.org", faculty1.getEmail());
        assertEquals(lessonList, faculty1.getLessons());
        assertEquals("Doe", faculty1.getLastName());
        assertEquals(1, faculty1.getId().intValue());
        assertEquals("Jane", faculty1.getFirstName());
        assertTrue(facultyServiceImpl.getAllFaculties().isEmpty());
    }



    @Test
    void testEditFaculty2() {
        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        when(facultyDao.save((Faculty) any())).thenReturn(faculty);
        doThrow(new NotFoundException("An error occurred")).when(userService).updateUser((Integer) any(), (String) any());

        Faculty faculty1 = new Faculty();
        faculty1.setEmail("jane.doe@example.org");
        faculty1.setFirstName("Jane");
        faculty1.setId(1);
        faculty1.setLastName("Doe");
        faculty1.setLessons(new ArrayList<>());
        assertThrows(NotFoundException.class, () -> facultyServiceImpl.editFaculty(faculty1));
        verify(userService).updateUser((Integer) any(), (String) any());
    }
}

