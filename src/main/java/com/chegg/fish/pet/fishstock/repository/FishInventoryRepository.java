package com.chegg.fish.pet.fishstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chegg.fish.pet.fishstock.model.FishInventory;

public interface FishInventoryRepository extends JpaRepository<FishInventory, Integer> {

}
