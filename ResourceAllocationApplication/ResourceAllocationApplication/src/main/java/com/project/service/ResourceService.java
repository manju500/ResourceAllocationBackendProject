package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Resource;
import com.project.repository.ResourceRepository;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public List<Resource> filterResources(List<String> skills, int maxExperience) {
        return resourceRepository.findBySkillsInAndExperienceYearsLessThan(skills, maxExperience);
    }

    public Resource addResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }
}
