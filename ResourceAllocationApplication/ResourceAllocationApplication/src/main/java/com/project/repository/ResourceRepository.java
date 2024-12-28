package com.project.repository;

import com.project.entity.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
	List<Resource> findBySkillsInAndExperienceYearsLessThan(List<String> skills, int maxExperience);
}
