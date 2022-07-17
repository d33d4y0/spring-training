package com.github.d33d4y0.training.elasticsearch.domain;

public class CourseDomain {

	private String courseId;
	private String courseName;
	private float grade;

	public CourseDomain() {
	}
	
	public CourseDomain(String courseId, String courseName, float grade) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.grade = grade;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

}
