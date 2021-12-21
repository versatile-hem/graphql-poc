package com.chegg.fish.pet.fishstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chegg.fish.pet.fishstock.model.FishMaster;

public interface FishRepository extends JpaRepository<FishMaster, Integer> {

}
