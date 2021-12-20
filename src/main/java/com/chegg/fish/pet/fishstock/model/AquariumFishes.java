package com.chegg.fish.pet.fishstock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
public class AquariumFishes {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "aquarium_id", nullable = false)
	private Aquarium aquarium;

	@ManyToOne
	@JoinColumn(name = "fish_id", nullable = false)
	private Fish fish;

	private int fishCount;

}
