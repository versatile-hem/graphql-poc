package com.chegg.fish.pet.fishstock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GraphQLWebClientTest {

	@Autowired
	MockMvc mockMvc;

	int aqID = 19;

	@Test
	@Order(0)
	void makeAquarium() throws Exception {

		MvcResult mvcResult = mockMvc
				.perform(post("/rest/aquariums")
						.content("{ mutation \n" + "{\n" + "\n" + "makeAquarium( \n" + "	glassType: \"TUFFEND\",\n"
								+ "	volume: 20.9,\n" + "	volumeUnit: \"LITER\" ,\n" + "	shape: \"HALF_MOON\"\n"
								+ "  ){\n" + "	 id\n" + "			glassType  \n" + "			volume\n"
								+ "			volumeUnit\n" + "			shape  \n" + "			fishes {\n"
								+ "				fishCount\n" + "				fish { \n" + "					id\n"
								+ "					species\n" + "					color\n" + "				}\n"
								+ "			\n" + "			}\n" + "}\n" + "\n" + "} }")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	@Order(1)
	void addAndRemoveFish() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/rest/aquariums")
				.content("{  \n" + "mutation \n" + "{\n" + "updateFish(\n" + "	id: 19,\n" + "	action: \"ADD\",\n"
						+ "	fishes :[\n" + "		{			 \n" + "			fishId: 4,\n"
						+ "			count: 2\n" + "		}\n" + "	]\n" + "  ){\n" + "	id\n" + "	glassType\n"
						+ "	volume\n" + "	volumeUnit\n" + "	 fishes {\n" + "				fishCount\n"
						+ "                fish { \n" + "                    id\n" + "                    species\n"
						+ "                    color\n" + "										numberFins\n"
						+ "                }\n" + "			}\n" + "}\n" + "\n" + "\n" + "}  }")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();

	}

	@Test
	@Order(2)
	void getAuqarium() throws Exception {
		mockMvc.perform(post("/rest/aquariums")
				.content("{\n" + "	 	aquarium(id: " + aqID + ") {\n" + "	    id\n" + "			glassType  \n"
						+ "			volume\n" + "			volumeUnit\n" + "			shape  \n"
						+ "			fishes {\n" + "				fishCount\n" + "				fish { \n"
						+ "					id\n" + "					species\n" + "					color\n"
						+ "				}\n" + "			\n" + "			}\n" + "	 }\n" + "	  allFishes {\n"
						+ "			id\n" + "			species\n" + "			color\n" + "			numberFins \n"
						+ "	 }\n" + "	\n" + "}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();
	}

}
