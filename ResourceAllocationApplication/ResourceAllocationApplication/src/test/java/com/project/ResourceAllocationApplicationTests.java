package com.project;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.project.entity.Resource;
import com.project.repository.ResourceRepository;
import com.project.service.ResourceService;

@SpringBootTest
class ResourceAllocationApplicationTests {

	@Autowired
    private ResourceService resourceService;

    @MockBean
    private ResourceRepository resourceRepository;

    @Test
    public void testFilterResources() {
        List<String> skills = Arrays.asList("Java", "Redis", "Javascript");
        int maxExperience = 10;

        Resource resource1 = new Resource();
        resource1.setResourceName("Thompson");
        resource1.setExperienceYears(7);
        resource1.setSkills(Arrays.asList("Java", "Redis", "Javascript"));

        Resource resource2 = new Resource();
        resource2.setResourceName("Kumar");
        resource2.setExperienceYears(3);
        resource2.setSkills(Arrays.asList("Java", "Redis", "Javascript"));

        when(resourceRepository.findBySkillsInAndExperienceYearsLessThan(skills, maxExperience))
                .thenReturn(Arrays.asList(resource1, resource2));

        List<Resource> result = resourceService.filterResources(skills, maxExperience);

        assertThat(result).hasSize(2);
        assertThat(result).extracting("resourceName").containsExactly("Thompson", "Kumar");
    }

    @Test
    public void testAddResource() {
        Resource resource = new Resource();
        resource.setResourceName("Dennis");
        resource.setExperienceYears(4);
        resource.setSkills(Arrays.asList("Java", "Spring"));

        when(resourceRepository.save(resource)).thenReturn(resource);

        Resource savedResource = resourceService.addResource(resource);

        assertThat(savedResource.getResourceName()).isEqualTo("Dennis");
        assertThat(savedResource.getExperienceYears()).isEqualTo(4);
    }

    @Test
    public void testGetAllResources() {
        Resource resource1 = new Resource();
        resource1.setResourceName("Aisha");
        resource1.setExperienceYears(9);
        resource1.setSkills(Arrays.asList("Angular", "Docker"));

        Resource resource2 = new Resource();
        resource2.setResourceName("Maya");
        resource2.setExperienceYears(5);
        resource2.setSkills(Arrays.asList("Spring", "Hibernate"));

        when(resourceRepository.findAll()).thenReturn(Arrays.asList(resource1, resource2));

        List<Resource> result = resourceService.getAllResources();

        assertThat(result).hasSize(2);
        assertThat(result).extracting("resourceName").containsExactly("Aisha", "Maya");
    }


}
