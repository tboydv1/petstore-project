package com.petstore;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.petstore.model.Pet;
import com.petstore.repository.PetRepository;
import com.petstore.service.PetService;
import com.petstore.service.PetServiceImpl;

class PetServiceImplTest {
	
	@Mock
	PetRepository petRepository;
	
	@InjectMocks
	PetServiceImpl petService;

	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		petRepository = mock(PetRepository.class);
	}

	@Test
	void findAllPetsTest() {
		
		List<Pet> pets = new ArrayList<>();

				
		when(petService.findAll()).thenReturn(pets);
		petRepository.findAll();
		
		verify(petRepository, times(1)).findAll();
		
	}

}
