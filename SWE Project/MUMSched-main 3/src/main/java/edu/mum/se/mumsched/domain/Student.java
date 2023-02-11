package edu.mum.se.mumsched.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends Person {

	@ManyToOne
	@JoinColumn(name="entry_id")
	private Entry entry;
	
	@OneToMany(mappedBy = "student")
	private List<StudentLesson> studentLessons;
	
	@Column
	private Track track;

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public List<StudentLesson> getStudentLessons() {
		return studentLessons;
	}

	public void setStudentLessons(List<StudentLesson> studentLessons) {
		this.studentLessons = studentLessons;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}
}
