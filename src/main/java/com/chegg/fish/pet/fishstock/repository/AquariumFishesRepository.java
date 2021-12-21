package com.chegg.fish.pet.fishstock.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.chegg.fish.pet.fishstock.model.AquariumFishes;

public interface AquariumFishesRepository extends JpaRepository<AquariumFishes, Integer> {

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE chegg.aquarium_fishes set fish_count=fish_count + ?1 WHERE aquarium_id=?2 and fish_id=?3")
	void addFishes(int no, int aid, int fid);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE chegg.aquarium_fishes set fish_count=fish_count - ?1 WHERE aquarium_id=?2 and fish_id=?3")
	void removeFishes(int no, int aid, int fid);

	@Query(nativeQuery = true, value = "Select * from chegg.aquarium_fishes WHERE aquarium_id=?1 and fish_id =?2")
	Optional<AquariumFishes> findByAquariumIdAndFishId(int aid, int fid);

}
