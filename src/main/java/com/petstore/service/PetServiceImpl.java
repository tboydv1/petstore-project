package com.petstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petstore.model.Pet;
import com.petstore.repository.PetRepository;

@Service("petservice")
public class PetServiceImpl implements PetService{

	
	@Autowired
	PetRepository petRepository;
	
	@Override
	public List<Pet> findAll() {
		// TODO Auto-generated method stub
		return petRepository.findAll();
	}

	@Override
	public Pet findOne(Integer petId) {
		// TODO Auto-generated method stub
		return petRepository.getOne(petId);
	}

	@Override
	public Pet save(Pet pet) {
		// TODO Auto-generated method stub
		return petRepository.save(pet);
	}

}
