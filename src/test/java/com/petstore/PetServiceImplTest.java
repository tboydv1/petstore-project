package com.petstore;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.petstore.model.Pet;
import com.petstore.repository.PetRepository;
import com.petstore.service.PetServiceImpl;

class PetServiceImplTest {
	
	
	Logger log = Logger.getLogger(getClass().getName());
	
	@Mock
	PetRepository petRepository;
	
	@InjectMocks
	PetServiceImpl petService;
	
	List<Pet> pets;
	
	Pet pet;


	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		petRepository = mock(PetRepository.class);
		
		pets = new ArrayList<>();
		pet = new Pet();
	}

	@Test
	void findAllPetsTest() {
		

				
		when(petService.findAll()).thenReturn(pets);
		petRepository.findAll();
		
		verify(petRepository, times(1)).findAll();
		
	}
	
	@Test
	void findOnePetTest() {
		
		int petId = 1;
		
		when(petService.findOne(petId)).thenReturn(pet);
		petRepository.findById(petId);
		
		verify(petRepository, times(1)).findById(petId);
	}

	@Test
	void savePetTest() {
		
		when(petService.save(pet)).thenReturn(pet);
		petRepository.save(pet);
		
		verify(petRepository, times(1)).save(pet);
	}

}
