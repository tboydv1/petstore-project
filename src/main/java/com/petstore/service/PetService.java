package com.petstore.service;

import java.util.List;

import com.petstore.model.Pet;

public interface PetService {
 
	public List<Pet> findAll();
	
	public Pet findOne(Integer petId);
	
	public Pet save(Pet pet);
	
}
