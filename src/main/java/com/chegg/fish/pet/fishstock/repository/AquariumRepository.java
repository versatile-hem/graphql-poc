package com.chegg.fish.pet.fishstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chegg.fish.pet.fishstock.model.Aquarium;

public interface AquariumRepository extends JpaRepository<Aquarium, Integer> {

}
