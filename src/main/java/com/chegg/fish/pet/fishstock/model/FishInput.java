package com.chegg.fish.pet.fishstock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FishInput {

	private int fishId;
	
	private int count; 
}
