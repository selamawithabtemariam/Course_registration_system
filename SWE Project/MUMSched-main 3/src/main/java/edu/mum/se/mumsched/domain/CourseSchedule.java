package edu.mum.se.mumsched.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "course_schedule")
public class CourseSchedule {

    public CourseSchedule(){}

    private CourseSchedule(Block block, Course course){
        this.block = block;
        this.course = course;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "block_id")
    private Block block;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public static CourseSchedule createCourseSchedule(Block block, Course course){
        return new CourseSchedule(block, course);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String toString() {
        return "{ id: " + this.id + "," +
                    "block: {" + this.getBlock() + "}," +
                    "course: {" + this.getCourse() + "}" +
                "}";


    }
}
