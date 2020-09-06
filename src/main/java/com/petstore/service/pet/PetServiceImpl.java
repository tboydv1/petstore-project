package com.petstore.service.pet;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.petstore.client.dto.PetDto;
import com.petstore.exception.PetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petstore.model.Pet;
import com.petstore.repository.PetRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PetServiceImpl implements PetService{


	@Autowired
	PetRepository petRepository;

	@Autowired
	PetMapper petMapper;

	Logger log = Logger.getLogger(getClass().getName());

	@Override
	public Pet save(PetDto petDto)
	{
		Pet pet = new Pet();
		petMapper.updatePetFromDto(petDto, pet);
		log.info("Pet details after mapping --> "+ pet);
		return petRepository.save(pet);
	}

	@Override
	public Optional<Pet> findById(Integer petId) {
		return petRepository.findById(petId);

	}

	@Override
	public Pet update(PetDto petDto) throws PetNotFoundException {

		Pet savedPet = petRepository.findById(petDto.getId()).orElseThrow(PetNotFoundException::new);
		petMapper.updatePetFromDto(petDto, savedPet);
		return petRepository.save(savedPet);
	}

	@Override
	public void delete(Integer petid) {
		petRepository.deleteById(petid);
	}

	@Override
	public List<Pet> findAll() {
		return petRepository.findAll();
	}

}
