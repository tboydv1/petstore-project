package com.petstore.service.pet;

import java.util.List;
import java.util.Optional;

import com.petstore.client.dto.PetDto;
import com.petstore.exception.PetNotFoundException;
import com.petstore.model.Pet;

public interface PetService {

	Pet save(PetDto dto);

	Optional<Pet> findById(Integer petId);

	Pet update(PetDto petDto) throws PetNotFoundException;

	void delete(Integer petId);

	List<Pet> findAll();
	
}
