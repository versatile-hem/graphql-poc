package com.chegg.fish.pet.fishstock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chegg.fish.pet.fishstock.enums.GlassType;
import com.chegg.fish.pet.fishstock.enums.Shape;
import com.chegg.fish.pet.fishstock.enums.VolumeUnit;
import com.chegg.fish.pet.fishstock.model.Aquarium;
import com.chegg.fish.pet.fishstock.model.Fish;
import com.chegg.fish.pet.fishstock.repository.AquariumRepository;
import com.chegg.fish.pet.fishstock.repository.FishRepository;

import graphql.schema.DataFetcher;

/**
 * 
 * 
 * 
 * @author hem
 *
 */
@Service
public class AquariumService {

	@Autowired
	private AquariumRepository aquariumRepository;

	@Autowired
	private FishRepository fishRepository;

	/**
	 * 
	 * @param aquarium
	 * @return
	 */
	public Aquarium updateFishInAquarium(Aquarium aquarium) {

		if (aquarium.getId() <= 0) {
			aquarium = new Aquarium();
		} else {
			aquarium = aquariumRepository.findById(aquarium.getId()).orElseThrow(RuntimeException::new);
		}
		if (aquarium.getGlassType() != null)
			aquarium.setGlassType(aquarium.getGlassType());
		if (aquarium.getShape() != null)
			aquarium.setShape(aquarium.getShape());
		if (aquarium.getVolume() <= 0)
			aquarium.setVolume(aquarium.getVolume());
		if (aquarium.getVolumeUnit() != null)
			aquarium.setVolumeUnit(aquarium.getVolumeUnit());
		aquarium.setFishes(aquarium.getFishes());

		return aquariumRepository.save(aquarium);
	}

	public DataFetcher<List<Aquarium>> getAllAquarium() {
		return env -> aquariumRepository.findAll();
	}

	public DataFetcher<Aquarium> getAquarium() {
		return environment -> aquariumRepository.findById(environment.getArgument("id")).get();
	}

	public DataFetcher<Aquarium> fillAquarium() {
		return environment -> {
			Aquarium aquarium;
			if (environment.getArgument("id") == null) {
				aquarium = new Aquarium();
			} else {
				aquarium = aquariumRepository.findById(environment.getArgument("id"))
						.orElseThrow(RuntimeException::new);
			}
			if (environment.getArgument("glassType") != null)
				aquarium.setGlassType(GlassType.valueOf(environment.getArgument("glassType")));
			if (environment.getArgument("shape") != null)
				aquarium.setShape(Shape.valueOf(environment.getArgument("shape")));
			if (environment.getArgument("volume") != null)
				aquarium.setVolume(environment.getArgument("volume"));
			if (environment.getArgument("volumeUnit") != null)
				aquarium.setVolumeUnit(VolumeUnit.valueOf(environment.getArgument("volumeUnit")));
			aquarium.setFishes(environment.getArgument("fished"));

			return aquariumRepository.save(aquarium);
		};
	}

	public DataFetcher<List<Fish>> getAllFishes() {
		return env -> fishRepository.findAll();
	}

}
