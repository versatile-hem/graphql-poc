package com.chegg.fish.pet.fishstock.service.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chegg.fish.pet.fishstock.model.Aquarium;
import com.chegg.fish.pet.fishstock.repository.AquariumRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class AquariumResolver implements GraphQLMutationResolver {

	@Autowired
	private AquariumRepository aquariumRepository;

	public Aquarium addFish(Aquarium student) {
		return aquariumRepository.save(student);
	}

}
