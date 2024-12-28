package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.FilterRequest;
import com.project.entity.Resource;
import com.project.service.ResourceService;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping("/filter")
    public ResponseEntity<List<Resource>> filterResources(@RequestBody FilterRequest filterRequest) {
        List<Resource> resources = resourceService.filterResources(filterRequest.getSkills(), filterRequest.getMaxExperience());
        return ResponseEntity.ok(resources);
    }

    @PostMapping("/add")
    public ResponseEntity<Resource> addResource(@RequestBody Resource resource) {
        return ResponseEntity.ok(resourceService.addResource(resource));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Resource>> getAllResources() {
        return ResponseEntity.ok(resourceService.getAllResources());
    }
}
