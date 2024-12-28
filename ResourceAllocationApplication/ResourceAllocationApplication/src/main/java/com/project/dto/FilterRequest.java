package com.project.dto;

import java.util.List;

import lombok.Data;

@Data
public class FilterRequest {
	
	private List<String> skills;
	
	private int maxExperience;

}
