package com.github.d33d4y0.training.elasticsearch.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.github.d33d4y0.training.elasticsearch.entity.StudentEntity;

@Repository
public interface StudentRepository extends ElasticsearchRepository<StudentEntity, String> {

	public StudentEntity findByCitizenId(String citizenId);
	public List<StudentEntity> findByAge(int age);
	
//	Equality Condition Keywords
	public List<StudentEntity> findByCitizenIdIsNot(String name);
	public List<StudentEntity> findByIsGraduatedFalse();
	
//	Similarity Condition Keywords
	public List<StudentEntity> findByNameStartingWith(String prefix);
	public List<StudentEntity> findByNameContaining(String prefix);
	public List<StudentEntity> findByNameEndingWith(String prefix);
	public List<StudentEntity> findByNameLike(String likePattern);
	
//	Comparison Condition Keywords
	public List<StudentEntity> findByAgeLessThan(int age);
	public List<StudentEntity> findByAgeLessThanEqual(int age);
	public List<StudentEntity> findByAgeGreaterThan(int age);
	public List<StudentEntity> findByAgeBetween(int startAge, int endAge);
	public List<StudentEntity> findByRegisteredDateTimeAfter(LocalDateTime birthDate);
	public List<StudentEntity> findByRegisteredDateTimeBefore(LocalDateTime birthDate);
	
//	Nested
	public List<StudentEntity> findByAddressDistrict(String district);
	
//	OR
	public List<StudentEntity> findByNameOrAge(String name, int age);
	public List<StudentEntity> findByNameOrCitizenId(String name, String citizenId);
	
//	Sorting
	public List<StudentEntity> findByAgeOrderByRegisteredDateTimeDesc(int age);
}
