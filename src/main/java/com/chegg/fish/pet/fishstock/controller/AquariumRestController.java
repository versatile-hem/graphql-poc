package com.chegg.fish.pet.fishstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chegg.fish.pet.fishstock.GraphQLConfig;

@RequestMapping(value = "/rest/aquariums")
@RestController
public class AquariumRestController {

	@Autowired
	private GraphQLConfig graphQLService; 
	
	
	@PostMapping
	public ResponseEntity<Object> executeAquaQuery(@RequestBody String query) {
		var executionResult = graphQLService.getGraphQl().execute(query);
		return new ResponseEntity<>(executionResult, HttpStatus.OK);
	}
	
	
	
}
