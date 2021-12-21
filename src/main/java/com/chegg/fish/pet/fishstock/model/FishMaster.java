package com.chegg.fish.pet.fishstock.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.chegg.fish.pet.fishstock.enums.FishColor;
import com.chegg.fish.pet.fishstock.enums.FishSpecies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "fish")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FishMaster {


	@Id
	private int id;
	
	@Enumerated(EnumType.STRING)
	private FishSpecies species;
	
	@Enumerated(EnumType.STRING)
	private FishColor color;
	
	private int numberFins;
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="fish_fight",
		joinColumns={@JoinColumn(name="fish_id_1")},
		inverseJoinColumns={@JoinColumn(name="fish_id_2")})
	private Set<FishMaster> danger = new HashSet<>();

	
	@ManyToMany(mappedBy="danger")
	private Set<FishMaster> dangerFishes = new HashSet<>();

	
	
	 
}
