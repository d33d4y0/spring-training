package com.github.d33d4y0.training.es.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.d33d4y0.training.es.domain.Address;
import com.github.d33d4y0.training.es.domain.CourseDomain;
import com.github.d33d4y0.training.es.entity.StudentEntity;
import com.github.d33d4y0.training.es.repository.StudentRepository;

@Service
public class FindByService {

	@Autowired
	private StudentRepository studentRepo;

	@PostConstruct
	private void init() {
//		initialize data
		StudentEntity student1 = new StudentEntity();
		student1.setAddress(new Address("Thailand", "Bangkok", "Pak Khlong Phasi Charoen"));
		Map<String, CourseDomain> registeredCourse1 = new HashMap<>();
		CourseDomain course1 = new CourseDomain("c1", "c programming", 3.5f);
		CourseDomain course2 = new CourseDomain("c2", "python programming", 3.0f);
		student1.setId("s1");
		student1.setName("John Doe");
		student1.setCitizenId("2222222222222");
		student1.setAge(24);
		student1.setRegisteredDateTime(LocalDateTime.now());
		student1.setTags(Arrays.asList("fourth year", "scholarship"));
		student1.setRegisteredCourses(registeredCourse1);
		registeredCourse1.put(course1.getCourseId(), course1);
		registeredCourse1.put(course2.getCourseId(), course2);

		StudentEntity student2 = new StudentEntity();
		student2.setAddress(new Address("Thailand", "Bangkok", "Phaya Thai"));
		Map<String, CourseDomain> registeredCourse2 = new HashMap<>();
		CourseDomain course3 = new CourseDomain("c1", "c programming", 2.5f);
		CourseDomain course4 = new CourseDomain("c3", "java programming", 4.0f);
		student2.setId("s2");
		student2.setName("D33d4y es");
		student2.setCitizenId("1111111111111");
		student2.setAge(24);
		student2.setRegisteredDateTime(LocalDateTime.now().plusDays(5));
		student2.setTags(Arrays.asList("fourth year"));
		student2.setRegisteredCourses(registeredCourse2);
		registeredCourse2.put(course3.getCourseId(), course3);
		registeredCourse2.put(course4.getCourseId(), course4);

		studentRepo.saveAll(Arrays.asList(student1, student2));
	}

	public StudentEntity findByCitizenId() {
		StudentEntity entity = studentRepo.findByCitizenId("1111111111111");
		return entity;
	}

	public List<StudentEntity> findByAge() {
		List<StudentEntity> entities = studentRepo.findByAge(24);
		return entities;
	}

	public List<StudentEntity> findByCitizenIdIsNot() {
		List<StudentEntity> entities = studentRepo.findByCitizenIdIsNot("1111111111111");
		return entities;
	}

	public List<StudentEntity> findByIsGraduatedFalse() {
		List<StudentEntity> entities = studentRepo.findByIsGraduatedFalse();
		return entities;
	}

	public List<StudentEntity> findByNameStartingWith() {
		List<StudentEntity> entities = studentRepo.findByNameStartingWith("D33d4y");
		return entities;
	}

	public List<StudentEntity> findByAgeLessThan() {
		List<StudentEntity> entities = studentRepo.findByAgeLessThan(30);
		return entities;
	}

	public List<StudentEntity> findByAgeBetween() {
		List<StudentEntity> entities = studentRepo.findByAgeBetween(20, 30);
		return entities;
	}

	public List<StudentEntity> findByRegisteredDateTimeAfter() {
		List<StudentEntity> entities = studentRepo.findByRegisteredDateTimeAfter(LocalDateTime.now().plusDays(1));
		return entities;
	}
	
	public List<StudentEntity> findByAgeOrderByRegisteredDateTimeDesc() {
		List<StudentEntity> entities = studentRepo.findByAgeOrderByRegisteredDateTimeDesc(24);
		return entities;
	}
	
	public List<StudentEntity> findByAddressDistrict() {
		List<StudentEntity> entities = studentRepo.findByAddressDistrict("Pak Khlong Phasi Charoen");
		return entities;
	}
}
