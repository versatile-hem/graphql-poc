package com.chegg.fish.pet.fishstock.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chegg.fish.pet.exceptions.FishException;
import com.chegg.fish.pet.exceptions.FishExceptionCode;
import com.chegg.fish.pet.fishstock.enums.Action;
import com.chegg.fish.pet.fishstock.enums.GlassType;
import com.chegg.fish.pet.fishstock.enums.Shape;
import com.chegg.fish.pet.fishstock.enums.VolumeUnit;
import com.chegg.fish.pet.fishstock.model.Aquarium;
import com.chegg.fish.pet.fishstock.model.AquariumFishes;
import com.chegg.fish.pet.fishstock.model.FishInput;
import com.chegg.fish.pet.fishstock.model.FishMaster;
import com.chegg.fish.pet.fishstock.repository.AquariumFishesRepository;
import com.chegg.fish.pet.fishstock.repository.AquariumRepository;
import com.chegg.fish.pet.fishstock.repository.FishRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * 
 * @author hem
 *
 */
@Slf4j
@Service
public class AquariumService {

	@Autowired
	private AquariumRepository aquariumRepository;

	@Autowired
	private AquariumFishesRepository aquariumFishesRepository;

	@Autowired
	private FishRepository fishRepository;

	@Value("${com.chegg.min.tank.size}")
	double minLiter;

	@Value("${com.chegg.min.fin.no}")
	int fins;

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

	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public DataFetcher<List<Aquarium>> getAllAquarium() {
		return env -> aquariumRepository.findAll();
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public DataFetcher<Aquarium> getAquarium() {
		return environment -> aquariumRepository.findById(environment.getArgument("id")).get();
	}

	/**
	 * 
	 * @return
	 */
	public DataFetcher<List<FishMaster>> getAllFishes() {
		return env -> fishRepository.findAll();
	}
	
	

	/**
	 * 
	 * @return
	 */
	public DataFetcher<Aquarium> makeAquarium() {
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
			return aquariumRepository.save(aquarium);
		};
	}

	/**
	 * 
	 * @return
	 */
	public DataFetcher<Aquarium> updateFish() {
		return environment -> {
			var aquarium = aquariumRepository.findById(environment.getArgument("id"))
					.orElseThrow(RuntimeException::new);
			final Action action = Action.valueOf(environment.getArgument("action"));
			var mapper = new ObjectMapper();
			List<FishInput> afList = mapper.convertValue(environment.getArgument("fishes"),
					new TypeReference<List<FishInput>>() {
					});
			afList.forEach(val -> {
				if (val.getCount() <= 0 || val.getCount() > 10000) {
					throw new FishException(FishExceptionCode.INVALID_PARAM);
				}
				var optional = aquariumFishesRepository.findByAquariumIdAndFishId(aquarium.getId(), val.getFishId());
				switch (action) {
				case ADD:
					addFish(optional, val, aquarium);
					break;
				case REMOVE:
					removeFish(optional, val, aquarium);
					break;
				default:
					break;
				}

			});
			return aquarium;
		};
	}

	/**
	 * 
	 * @param optional
	 * @param val
	 * @param aquarium
	 */
	private void addFish(Optional<AquariumFishes> optional, FishInput val, Aquarium aquarium) {
		FishMaster fish = this.fishRepository.findById(val.getFishId()).orElseThrow(RuntimeException::new);
		if (fish.getNumberFins() >= this.fins && VolumeUnit.LITER.equals(aquarium.getVolumeUnit())
				&& aquarium.getVolume() <= minLiter) {
			throw new FishException(FishExceptionCode.CAN_NOT_FIT_FISH);
		}
		log.info("fish param : {}", fish);
		if (optional.isPresent()) {
		//	List<Integer> dangerous = fish.getDangerFishes().stream().flatMap(fs -> Stream.of(fs.getId()))
		//			.collect(Collectors.toList());
			log.info("Validating if any dangerous fish in acqu...");
			aquarium.getFishes().forEach(af -> {
				if (fish.getDangerFishes().contains(af.getFish())) {
					throw new FishException(FishExceptionCode.CAN_NOT_STAY_WITH_FISHES);
				}
			});
			log.info("fish param : " + val + " aid :" + aquarium.getId());
			aquariumFishesRepository.addFishes(val.getCount(),  aquarium.getId(), val.getFishId());
		} else {
			var aquariumFishes = new AquariumFishes();
			aquariumFishes.setAquarium(aquarium);
			aquariumFishes.setFish(fish);
			aquariumFishes.setFishCount(val.getCount());
			aquariumFishesRepository.save(aquariumFishes);
		}

	}

	/**
	 * 
	 * 
	 * 
	 * @param optional
	 * @param val
	 * @param aquarium
	 */
	private void removeFish(Optional<AquariumFishes> optional, FishInput val, Aquarium aquarium) {
		if (optional.isPresent()) {
			if (optional.get().getFishCount() < val.getCount()) {
				log.error("Invalid count for fish to remove");
				throw new FishException(FishExceptionCode.INSUFFICENT_FISH);
			}
			log.info("fish param : " + val + " aid :" + aquarium.getId());
			aquariumFishesRepository.removeFishes(val.getCount(),aquarium.getId(), val.getFishId());
		} else {
			throw new FishException(FishExceptionCode.FISH_NOT_PRESENT);
		}

	}
 

}
