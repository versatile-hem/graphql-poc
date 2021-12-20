package com.chegg.fish.pet.fishstock.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.chegg.fish.pet.fishstock.enums.FishColor;
import com.chegg.fish.pet.fishstock.enums.FishSpecies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fish {


	@Id
	private int id;
	
	private FishSpecies species;
	
	private FishColor color;
	
	private int numberFins;
	

	

	
}
