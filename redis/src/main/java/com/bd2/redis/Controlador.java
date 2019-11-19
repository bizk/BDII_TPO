package com.bd2.redis;

import org.springframework.beans.factory.annotation.Autowired;

import com.bd2.redis.repositories.BuildingRepository;
import org.springframework.stereotype.Controller;

@Controller
public class Controlador {

	@Autowired
	BuildingRepository buildingRepository;

	public static void main(String [] args) {
		
	}
	
	public void getAllElements() {
		buildingRepository.getAllItems();
	}
}
