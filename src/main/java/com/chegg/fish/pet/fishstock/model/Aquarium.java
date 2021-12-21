package com.chegg.fish.pet.fishstock.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
import lombok.ToString;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Aquarium {

	@Id
	@GeneratedValue
	private int id;

	@Enumerated(EnumType.STRING)
	private GlassType glassType;

	private double volume;

	@Enumerated(EnumType.STRING)
	private VolumeUnit volumeUnit;

	@Enumerated(EnumType.STRING)
	private Shape shape;

	@OneToMany(mappedBy = "aquarium", fetch = FetchType.LAZY)
	private Set<AquariumFishes> fishes;
	
	
 

}
