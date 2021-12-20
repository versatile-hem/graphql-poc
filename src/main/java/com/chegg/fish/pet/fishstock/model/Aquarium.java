package com.chegg.fish.pet.fishstock.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.chegg.fish.pet.fishstock.enums.GlassType;
import com.chegg.fish.pet.fishstock.enums.Shape;
import com.chegg.fish.pet.fishstock.enums.VolumeUnit;

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
public class Aquarium {

	@Id
	@GeneratedValue
	private int id;

	private GlassType glassType;

	private double volume;

	private VolumeUnit volumeUnit;

	private Shape shape;

	@OneToMany(mappedBy = "aquarium")
	private Set<AquariumFishes> fishes;

	// private List<Fish> fishes;

}
