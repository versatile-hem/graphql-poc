package com.chegg.fish.pet.fishstock;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.chegg.fish.pet.fishstock.enums.FishColor;
import com.chegg.fish.pet.fishstock.enums.FishSpecies;
import com.chegg.fish.pet.fishstock.enums.GlassType;
import com.chegg.fish.pet.fishstock.enums.Shape;
import com.chegg.fish.pet.fishstock.enums.VolumeUnit;
import com.chegg.fish.pet.fishstock.model.Aquarium;
import com.chegg.fish.pet.fishstock.model.AquariumFishes;
import com.chegg.fish.pet.fishstock.model.FishMaster;
import com.chegg.fish.pet.fishstock.repository.AquariumRepository;
import com.chegg.fish.pet.fishstock.repository.FishRepository;
import com.chegg.fish.pet.fishstock.service.AquariumService;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

/**
 * 
 * @author hem
 *
 */
@Component
public class GraphQLConfig {

	@Value("classpath:fish.graphql")
	private Resource resource;

	private GraphQL graphQl;

	@Autowired
	private AquariumRepository aquariumRepository;

	@Autowired
	private AquariumService aquariumService;

	@Autowired
	private FishRepository fishRepository;

	@PostConstruct
	private void loadSchema() throws IOException {

		loadDataInHSQL();

		File file = resource.getFile();
		TypeDefinitionRegistry reg = new SchemaParser().parse(file);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(reg, wiring);
		graphQl = GraphQL.newGraphQL(graphQLSchema).build();

	}

	private void loadDataInHSQL() {
		Set<FishMaster> fishes = new HashSet<FishMaster>();
		FishMaster fishM = new FishMaster(1, FishSpecies.GOLDFISH, FishColor.indigo, 2, null, null);
		fishes.add(fishM);
		
		
		Stream.of(new FishMaster(1, FishSpecies.GOLDFISH, FishColor.indigo, 2, null, null),
				new FishMaster(2, FishSpecies.GOLDFISH, FishColor.Light_violet, 2, null, null),
				new FishMaster(3, FishSpecies.GUPPY, FishColor.deep_blue, 3, null, fishes),
				new FishMaster(4, FishSpecies.GUPPY, FishColor.indigo, 2, null, null)).forEach(fs -> {
					fishRepository.save(fs);
				});

		Set<AquariumFishes> fishes1 = new HashSet<AquariumFishes>();
		AquariumFishes fish = new AquariumFishes();
		fish.setFish(new FishMaster(1, FishSpecies.GOLDFISH, FishColor.indigo, 2,  null, null));
		fish.setFishCount(12);
		fishes1.add(fish);
		
		
		Stream.of(new Aquarium(1, GlassType.TUFFEND, 22.2, VolumeUnit.LITER, Shape.RECTANGULAR, fishes1),
				new Aquarium(2, GlassType.TUFFEND, 3.2, VolumeUnit.LITER, Shape.RECTANGULAR, fishes1),
				new Aquarium(3, GlassType.TUFFEND, 23.2, VolumeUnit.LITER, Shape.RECTANGULAR, null),
				new Aquarium(4, GlassType.TUFFEND, 123, VolumeUnit.LITER, Shape.RECTANGULAR, null)).forEach(aq -> {
					aquariumRepository.save(aq);
				});

	}

	private RuntimeWiring buildRuntimeWiring() {

		return RuntimeWiring.newRuntimeWiring()
				.type("Query",
						tw -> tw.dataFetcher("allAquarium", aquariumService.getAllAquarium())
								.dataFetcher("aquarium", aquariumService.getAquarium())
								.dataFetcher("allFishes", aquariumService.getAllFishes())
								)
				.type("Mutation", 
						tw -> tw.dataFetcher("makeAquarium", aquariumService.makeAquarium())
								.dataFetcher("updateFish", aquariumService.updateFish())
						)
				.build();
	}

	public GraphQL getGraphQl() {
		return graphQl;
	}


	
}
