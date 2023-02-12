package edu.mum.se.mumsched.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.mum.se.mumsched.component.SessionManager;
import edu.mum.se.mumsched.domain.Block;
import edu.mum.se.mumsched.domain.Course;
import edu.mum.se.mumsched.domain.CustomUser;
import edu.mum.se.mumsched.domain.Faculty;
import edu.mum.se.mumsched.domain.FacultySchedule;
import edu.mum.se.mumsched.domain.Lesson;
import edu.mum.se.mumsched.domain.StudentLesson;
import edu.mum.se.mumsched.service.BlockService;
import edu.mum.se.mumsched.service.FacultyService;
import edu.mum.se.mumsched.service.LessonService;
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

@ContextConfiguration(classes = {FacultyScheduleServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FacultyScheduleServiceImplTest {
    @MockBean
    private BlockService blockService;

    @Autowired
    private FacultyScheduleServiceImpl facultyScheduleServiceImpl;

    @MockBean
    private FacultyService facultyService;

    @MockBean
    private LessonService lessonService;

    @MockBean
    private SessionManager sessionManager;


    @Test
    void testGetSchedules() {
        when(blockService.getAllBlocks()).thenReturn(new ArrayList<>());
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(new ArrayList<>());
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        assertTrue(facultyScheduleServiceImpl.getSchedules().isEmpty());
        verify(blockService).getAllBlocks();
        verify(lessonService).getLessonByFacultyId((Integer) any());
        verify(sessionManager).getUser();
    }



    @Test
    void testGetSchedules2() {
        ArrayList<Block> blockList = new ArrayList<>();
        blockList.add(new Block());
        when(blockService.getAllBlocks()).thenReturn(blockList);
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(new ArrayList<>());
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        List<FacultySchedule> actualSchedules = facultyScheduleServiceImpl.getSchedules();
        assertEquals(1, actualSchedules.size());
        Block block = actualSchedules.get(0).getBlock();
        assertNull(block.getMonth());
        assertNull(block.getId());
        assertNull(block.getYear());
        verify(blockService).getAllBlocks();
        verify(lessonService).getLessonByFacultyId((Integer) any());
        verify(sessionManager).getUser();
    }



    @Test
    void testGetSchedules3() {
        when(blockService.getAllBlocks()).thenReturn(new ArrayList<>());
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(new ArrayList<>());
        when(sessionManager.getUser()).thenReturn(null);
        facultyScheduleServiceImpl.getSchedules();
    }


    @Test
    void testGetSchedules4() {

        ArrayList<Block> blockList = new ArrayList<>();
        blockList.add(null);
        when(blockService.getAllBlocks()).thenReturn(blockList);
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(new ArrayList<>());
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        facultyScheduleServiceImpl.getSchedules();
    }


    @Test
    void testGetSchedules5() {
        Block block = mock(Block.class);
        Block block1 = new Block();
        when(block.clone()).thenReturn(block1);

        ArrayList<Block> blockList = new ArrayList<>();
        blockList.add(block);
        when(blockService.getAllBlocks()).thenReturn(blockList);
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(new ArrayList<>());
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        List<FacultySchedule> actualSchedules = facultyScheduleServiceImpl.getSchedules();
        assertEquals(1, actualSchedules.size());
        assertSame(block1, actualSchedules.get(0).getBlock());
        verify(blockService).getAllBlocks();
        verify(block).clone();
        verify(lessonService).getLessonByFacultyId((Integer) any());
        verify(sessionManager).getUser();
    }


    @Test
    void testGetSchedules6() {
        Block block = mock(Block.class);
        when(block.getYear()).thenReturn(1);
        Block block1 = new Block();
        when(block.clone()).thenReturn(block1);

        ArrayList<Block> blockList = new ArrayList<>();
        blockList.add(block);
        when(blockService.getAllBlocks()).thenReturn(blockList);

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

        ArrayList<Lesson> lessonList = new ArrayList<>();
        lessonList.add(lesson);
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(lessonList);
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        List<FacultySchedule> actualSchedules = facultyScheduleServiceImpl.getSchedules();
        assertEquals(1, actualSchedules.size());
        assertSame(block1, actualSchedules.get(0).getBlock());
        verify(blockService).getAllBlocks();
        verify(block).clone();
        verify(block).getYear();
        verify(lessonService).getLessonByFacultyId((Integer) any());
        verify(sessionManager).getUser();
    }


    @Test
    void testGetSchedules7() {
        Block block = mock(Block.class);
        when(block.getYear()).thenReturn(1);
        Block block1 = new Block();
        when(block.clone()).thenReturn(block1);

        ArrayList<Block> blockList = new ArrayList<>();
        blockList.add(new Block());
        blockList.add(block);
        when(blockService.getAllBlocks()).thenReturn(blockList);

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

        ArrayList<Lesson> lessonList = new ArrayList<>();
        lessonList.add(lesson);
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(lessonList);
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        List<FacultySchedule> actualSchedules = facultyScheduleServiceImpl.getSchedules();
        assertEquals(2, actualSchedules.size());
        FacultySchedule getResult = actualSchedules.get(0);
        assertEquals(1, getResult.getLessonId().intValue());
        assertSame(block1, actualSchedules.get(1).getBlock());
        Course course = getResult.getCourse();
        assertNull(course.getName());
        Block block2 = getResult.getBlock();
        assertNull(block2.getYear());
        assertNull(block2.getMonth());
        assertNull(block2.getId());
        assertNull(course.getId());
        verify(blockService).getAllBlocks();
        verify(block).clone();
        verify(lessonService).getLessonByFacultyId((Integer) any());
        verify(sessionManager).getUser();
    }


    @Test
    void testGetSchedules8() {
        Block block = mock(Block.class);
        when(block.getMonth()).thenReturn(1);
        when(block.getYear()).thenReturn(null);
        Block block1 = new Block();
        when(block.clone()).thenReturn(block1);

        ArrayList<Block> blockList = new ArrayList<>();
        blockList.add(block);
        when(blockService.getAllBlocks()).thenReturn(blockList);

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

        ArrayList<Lesson> lessonList = new ArrayList<>();
        lessonList.add(lesson);
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(lessonList);
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        List<FacultySchedule> actualSchedules = facultyScheduleServiceImpl.getSchedules();
        assertEquals(1, actualSchedules.size());
        assertSame(block1, actualSchedules.get(0).getBlock());
        verify(blockService).getAllBlocks();
        verify(block).clone();
        verify(block).getMonth();
        verify(block).getYear();
        verify(lessonService).getLessonByFacultyId((Integer) any());
        verify(sessionManager).getUser();
    }


    @Test
    void testGetSchedules9() {

        Block block = mock(Block.class);
        when(block.getMonth()).thenReturn(1);
        when(block.getYear()).thenReturn(null);
        when(block.clone()).thenReturn(new Block());

        ArrayList<Block> blockList = new ArrayList<>();
        blockList.add(block);
        when(blockService.getAllBlocks()).thenReturn(blockList);

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

        ArrayList<Lesson> lessonList = new ArrayList<>();
        lessonList.add(lesson1);
        lessonList.add(lesson);
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(lessonList);
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        facultyScheduleServiceImpl.getSchedules();
    }


    @Test
    void testGetSchedules10() {

        Block block = mock(Block.class);
        when(block.getMonth()).thenReturn(1);
        when(block.getYear()).thenReturn(null);
        when(block.clone()).thenReturn(new Block());

        ArrayList<Block> blockList = new ArrayList<>();
        blockList.add(new Block());
        blockList.add(block);
        when(blockService.getAllBlocks()).thenReturn(blockList);

        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        Lesson lesson = mock(Lesson.class);
        when(lesson.getCourse()).thenReturn(null);
        when(lesson.getBlock()).thenReturn(new Block());
        doNothing().when(lesson).setBlock((Block) any());
        doNothing().when(lesson).setCourse((Course) any());
        doNothing().when(lesson).setFaculty((Faculty) any());
        doNothing().when(lesson).setId((Integer) any());
        doNothing().when(lesson).setStudentLessons((List<StudentLesson>) any());
        lesson.setBlock(new Block());
        lesson.setCourse(new Course());
        lesson.setFaculty(faculty);
        lesson.setId(1);
        lesson.setStudentLessons(new ArrayList<>());

        ArrayList<Lesson> lessonList = new ArrayList<>();
        lessonList.add(lesson);
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(lessonList);
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        facultyScheduleServiceImpl.getSchedules();
    }


    @Test
    void testGetSchedules11() {
        Block block = mock(Block.class);
        when(block.getMonth()).thenReturn(1);
        when(block.getYear()).thenReturn(null);
        Block block1 = new Block();
        when(block.clone()).thenReturn(block1);

        ArrayList<Block> blockList = new ArrayList<>();
        blockList.add(new Block());
        blockList.add(block);
        when(blockService.getAllBlocks()).thenReturn(blockList);

        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        Course course = mock(Course.class);
        Course course1 = new Course();
        when(course.clone()).thenReturn(course1);
        Lesson lesson = mock(Lesson.class);
        when(lesson.getId()).thenReturn(1);
        when(lesson.getCourse()).thenReturn(course);
        when(lesson.getBlock()).thenReturn(new Block());
        doNothing().when(lesson).setBlock((Block) any());
        doNothing().when(lesson).setCourse((Course) any());
        doNothing().when(lesson).setFaculty((Faculty) any());
        doNothing().when(lesson).setId((Integer) any());
        doNothing().when(lesson).setStudentLessons((List<StudentLesson>) any());
        lesson.setBlock(new Block());
        lesson.setCourse(new Course());
        lesson.setFaculty(faculty);
        lesson.setId(1);
        lesson.setStudentLessons(new ArrayList<>());

        ArrayList<Lesson> lessonList = new ArrayList<>();
        lessonList.add(lesson);
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(lessonList);
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        List<FacultySchedule> actualSchedules = facultyScheduleServiceImpl.getSchedules();
        assertEquals(2, actualSchedules.size());
        FacultySchedule getResult = actualSchedules.get(0);
        assertEquals(1, getResult.getLessonId().intValue());
        assertSame(block1, actualSchedules.get(1).getBlock());
        assertSame(course1, getResult.getCourse());
        Block block2 = getResult.getBlock();
        assertNull(block2.getYear());
        assertNull(block2.getMonth());
        assertNull(block2.getId());
        verify(blockService).getAllBlocks();
        verify(block).clone();
        verify(lessonService).getLessonByFacultyId((Integer) any());
        verify(lesson, atLeast(1)).getBlock();
        verify(lesson).getCourse();
        verify(lesson).getId();
        verify(lesson).setBlock((Block) any());
        verify(lesson).setCourse((Course) any());
        verify(lesson).setFaculty((Faculty) any());
        verify(lesson).setId((Integer) any());
        verify(lesson).setStudentLessons((List<StudentLesson>) any());
        verify(course).clone();
        verify(sessionManager).getUser();
    }



    @Test
    void testGetSchedules12() {

        Block block = mock(Block.class);
        when(block.getMonth()).thenReturn(1);
        when(block.getYear()).thenReturn(null);
        when(block.clone()).thenReturn(new Block());

        ArrayList<Block> blockList = new ArrayList<>();
        blockList.add(new Block());
        blockList.add(block);
        when(blockService.getAllBlocks()).thenReturn(blockList);

        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        Lesson lesson = mock(Lesson.class);
        when(lesson.getId()).thenReturn(1);
        when(lesson.getCourse()).thenReturn(mock(Course.class));
        when(lesson.getBlock()).thenReturn(null);
        doNothing().when(lesson).setBlock((Block) any());
        doNothing().when(lesson).setCourse((Course) any());
        doNothing().when(lesson).setFaculty((Faculty) any());
        doNothing().when(lesson).setId((Integer) any());
        doNothing().when(lesson).setStudentLessons((List<StudentLesson>) any());
        lesson.setBlock(new Block());
        lesson.setCourse(new Course());
        lesson.setFaculty(faculty);
        lesson.setId(1);
        lesson.setStudentLessons(new ArrayList<>());

        ArrayList<Lesson> lessonList = new ArrayList<>();
        lessonList.add(lesson);
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(lessonList);
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        facultyScheduleServiceImpl.getSchedules();
    }



    @Test
    void testGetSchedules13() {
        Block block = mock(Block.class);
        when(block.getMonth()).thenReturn(1);
        when(block.getYear()).thenReturn(null);
        Block block1 = new Block();
        when(block.clone()).thenReturn(block1);

        ArrayList<Block> blockList = new ArrayList<>();
        blockList.add(new Block());
        blockList.add(block);
        when(blockService.getAllBlocks()).thenReturn(blockList);

        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        Block block2 = mock(Block.class);
        when(block2.getYear()).thenReturn(1);
        Lesson lesson = mock(Lesson.class);
        when(lesson.getId()).thenReturn(1);
        when(lesson.getCourse()).thenReturn(mock(Course.class));
        when(lesson.getBlock()).thenReturn(block2);
        doNothing().when(lesson).setBlock((Block) any());
        doNothing().when(lesson).setCourse((Course) any());
        doNothing().when(lesson).setFaculty((Faculty) any());
        doNothing().when(lesson).setId((Integer) any());
        doNothing().when(lesson).setStudentLessons((List<StudentLesson>) any());
        lesson.setBlock(new Block());
        lesson.setCourse(new Course());
        lesson.setFaculty(faculty);
        lesson.setId(1);
        lesson.setStudentLessons(new ArrayList<>());

        ArrayList<Lesson> lessonList = new ArrayList<>();
        lessonList.add(lesson);
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(lessonList);
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        List<FacultySchedule> actualSchedules = facultyScheduleServiceImpl.getSchedules();
        assertEquals(2, actualSchedules.size());
        assertSame(block1, actualSchedules.get(1).getBlock());
        Block block3 = actualSchedules.get(0).getBlock();
        assertNull(block3.getYear());
        assertNull(block3.getMonth());
        assertNull(block3.getId());
        verify(blockService).getAllBlocks();
        verify(block).clone();
        verify(block).getYear();
        verify(lessonService).getLessonByFacultyId((Integer) any());
        verify(lesson, atLeast(1)).getBlock();
        verify(lesson).setBlock((Block) any());
        verify(lesson).setCourse((Course) any());
        verify(lesson).setFaculty((Faculty) any());
        verify(lesson).setId((Integer) any());
        verify(lesson).setStudentLessons((List<StudentLesson>) any());
        verify(block2, atLeast(1)).getYear();
        verify(sessionManager).getUser();
    }


    @Test
    void testGetSchedules14() {
        Block block = mock(Block.class);
        when(block.getMonth()).thenReturn(1);
        when(block.getYear()).thenReturn(null);
        Block block1 = new Block();
        when(block.clone()).thenReturn(block1);

        Block block2 = new Block();
        block2.setYear(1);

        ArrayList<Block> blockList = new ArrayList<>();
        blockList.add(block2);
        blockList.add(block);
        when(blockService.getAllBlocks()).thenReturn(blockList);

        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        Block block3 = mock(Block.class);
        when(block3.getMonth()).thenReturn(1);
        when(block3.getYear()).thenReturn(1);
        Lesson lesson = mock(Lesson.class);
        when(lesson.getId()).thenReturn(1);
        when(lesson.getCourse()).thenReturn(mock(Course.class));
        when(lesson.getBlock()).thenReturn(block3);
        doNothing().when(lesson).setBlock((Block) any());
        doNothing().when(lesson).setCourse((Course) any());
        doNothing().when(lesson).setFaculty((Faculty) any());
        doNothing().when(lesson).setId((Integer) any());
        doNothing().when(lesson).setStudentLessons((List<StudentLesson>) any());
        lesson.setBlock(new Block());
        lesson.setCourse(new Course());
        lesson.setFaculty(faculty);
        lesson.setId(1);
        lesson.setStudentLessons(new ArrayList<>());

        ArrayList<Lesson> lessonList = new ArrayList<>();
        lessonList.add(lesson);
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(lessonList);
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        List<FacultySchedule> actualSchedules = facultyScheduleServiceImpl.getSchedules();
        assertEquals(2, actualSchedules.size());
        assertSame(block1, actualSchedules.get(1).getBlock());
        Block block4 = actualSchedules.get(0).getBlock();
        assertEquals(1, block4.getYear().intValue());
        assertNull(block4.getMonth());
        assertNull(block4.getId());
        verify(blockService).getAllBlocks();
        verify(block).clone();
        verify(block).getYear();
        verify(lessonService).getLessonByFacultyId((Integer) any());
        verify(lesson, atLeast(1)).getBlock();
        verify(lesson).setBlock((Block) any());
        verify(lesson).setCourse((Course) any());
        verify(lesson).setFaculty((Faculty) any());
        verify(lesson).setId((Integer) any());
        verify(lesson).setStudentLessons((List<StudentLesson>) any());
        verify(block3).getMonth();
        verify(block3, atLeast(1)).getYear();
        verify(sessionManager).getUser();
    }


    @Test
    void testGetSchedules15() {

        Block block = mock(Block.class);
        when(block.getMonth()).thenReturn(1);
        when(block.getYear()).thenReturn(null);
        when(block.clone()).thenReturn(new Block());

        Block block1 = new Block();
        block1.setYear(1);

        ArrayList<Block> blockList = new ArrayList<>();
        blockList.add(block1);
        blockList.add(block);
        when(blockService.getAllBlocks()).thenReturn(blockList);

        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        Lesson lesson = mock(Lesson.class);
        when(lesson.getId()).thenReturn(1);
        when(lesson.getCourse()).thenReturn(mock(Course.class));
        when(lesson.getBlock()).thenReturn(mock(Block.class));
        doNothing().when(lesson).setBlock((Block) any());
        doNothing().when(lesson).setCourse((Course) any());
        doNothing().when(lesson).setFaculty((Faculty) any());
        doNothing().when(lesson).setId((Integer) any());
        doNothing().when(lesson).setStudentLessons((List<StudentLesson>) any());
        lesson.setBlock(new Block());
        lesson.setCourse(new Course());
        lesson.setFaculty(faculty);
        lesson.setId(1);
        lesson.setStudentLessons(new ArrayList<>());

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

        ArrayList<Lesson> lessonList = new ArrayList<>();
        lessonList.add(lesson1);
        lessonList.add(lesson);
        when(lessonService.getLessonByFacultyId((Integer) any())).thenReturn(lessonList);
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        facultyScheduleServiceImpl.getSchedules();
    }


    @Test
    void testAddLessonToFaculty() throws NotFoundException {
        when(blockService.getBlock((Integer) any())).thenReturn(new Block());

        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        when(facultyService.getFaculty((Integer) any())).thenReturn(faculty);
        doNothing().when(lessonService).createLesson((Block) any(), (Course) any(), (Faculty) any());
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        facultyScheduleServiceImpl.addLessonToFaculty("42", new Course());
        verify(blockService).getBlock((Integer) any());
        verify(facultyService).getFaculty((Integer) any());
        verify(lessonService).createLesson((Block) any(), (Course) any(), (Faculty) any());
        verify(sessionManager).getUser();
    }

    @Test
    void testAddLessonToFaculty2() throws NotFoundException {

        when(blockService.getBlock((Integer) any())).thenReturn(new Block());

        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        when(facultyService.getFaculty((Integer) any())).thenReturn(faculty);
        doNothing().when(lessonService).createLesson((Block) any(), (Course) any(), (Faculty) any());
        when(sessionManager.getUser()).thenReturn(null);
        facultyScheduleServiceImpl.addLessonToFaculty("42", new Course());
    }



    @Test
    void testAddLessonToFaculty3() throws NotFoundException {

        when(blockService.getBlock((Integer) any())).thenReturn(new Block());

        Faculty faculty = new Faculty();
        faculty.setEmail("jane.doe@example.org");
        faculty.setFirstName("Jane");
        faculty.setId(1);
        faculty.setLastName("Doe");
        faculty.setLessons(new ArrayList<>());
        when(facultyService.getFaculty((Integer) any())).thenReturn(faculty);
        doNothing().when(lessonService).createLesson((Block) any(), (Course) any(), (Faculty) any());
        when(sessionManager.getUser()).thenReturn(new CustomUser("janedoe", "iloveyou", new ArrayList<>(), 123));
        facultyScheduleServiceImpl.addLessonToFaculty("Block Id", new Course());
    }
}

