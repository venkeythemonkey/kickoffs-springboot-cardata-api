package com.fresco.carData;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.engine.support.discovery.SelectorResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.hamcrest.Matchers;

import javax.print.attribute.standard.Media;
import java.util.HashMap;
import java.util.Map;


import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class CarDataApplicationTests {

	@Autowired
	private MockMvc mvc;
	@Test
	@Order(1)

	public void getNullCarDataDetails() throws Exception{

		mvc.perform(get("/carData/list")
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(400));
	}

	@Test
	@Order(2)

	public void createCarData() throws Exception {

		mvc.perform(post("/carData/add")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(getCarDataDetails("Car", 2000, "TCS", "car", 1000, "Petrol", 5).toJSONString()))
				.andExpect(status().is(201));
		mvc.perform(post("/carData/add")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(getCarDataDetails("TATA", 1990, "Micro", "car", 20000, "Diesel", 7).toJSONString()))
				.andExpect(status().is(201));

	}
	public JSONObject getCarDataDetails(String model, int manufacturedYear, String company, String bodyType, float price, String fuelType, int seatingCapacity){
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("model", model);
		map.put("manufacturedYear", manufacturedYear);
		map.put("company", company);
		map.put("bodyType", bodyType);
		map.put("price", price);
		map.put("fuelType", fuelType);
		map.put("seatingCapacity", seatingCapacity);
		return new JSONObject(map);

	}
	@Test
	@Order(3)

	public void getCarDataDetails() throws Exception{
		mvc.perform(get("/carData/list")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				// First Array Element
				.andExpect(jsonPath("$.[0].model",containsStringIgnoringCase("Car")))
				.andExpect(jsonPath("$.[0].manufacturedYear", Matchers.is(2000)))
				.andExpect(jsonPath("$.[0].company",containsStringIgnoringCase("TCS")))
				.andExpect(jsonPath("$.[0].bodyType",containsStringIgnoringCase("Car")))
				.andExpect(jsonPath("$.[0].price",Matchers.is(1000.0)))
				.andExpect(jsonPath("$.[0].fuelType",containsStringIgnoringCase("Petrol")))
				.andExpect(jsonPath("$.[0].seatingCapacity", Matchers.is(5)))
				// Second Array Element
				.andExpect(jsonPath("$.[1].model",containsStringIgnoringCase("TATA")))
				.andExpect(jsonPath("$.[1].manufacturedYear", Matchers.is(1990)))
				.andExpect(jsonPath("$.[1].company",containsStringIgnoringCase("Micro")))
				.andExpect(jsonPath("$.[1].bodyType",containsStringIgnoringCase("car")))
				.andExpect(jsonPath("$.[1].price",Matchers.is(20000.0)))
				.andExpect(jsonPath("$.[1].fuelType",containsStringIgnoringCase("Diesel")))
				.andExpect(jsonPath("$.[1].seatingCapacity", Matchers.is(7)));
	}
	@Test
	@Order(4)

	public void patchCarDataWithId() throws Exception{
		mvc.perform(patch("/carData/update/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(UpdateCarData(300)
						.toJSONString())).andExpect(MockMvcResultMatchers.status().isOk());
		mvc.perform(patch("/carData/update/2")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(UpdateCarData(3466)
						.toJSONString())).andExpect(MockMvcResultMatchers.status().isOk());
	}

	public JSONObject UpdateCarData(int price){
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("price",price);
		return new JSONObject(map);
	}

	@Test
	@Order(5)

	public void deleteCarDataById() throws Exception{

		mvc.perform(delete("/carData/delete/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();
		mvc.perform(delete("/carData/delete/2")
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();

	}

	@Test
	@Order(6)

	public void deleteAndPatchCarDataById() throws Exception{
		mvc.perform(delete("/carData/delete/6")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(400))
				.andReturn();

		mvc.perform(delete("/carData/delete/7")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(400))
				.andReturn();

		mvc.perform(patch("/carData/update/4")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(UpdateCarData(3900)
						.toJSONString())).andExpect(MockMvcResultMatchers.status().is(400));
	}
}