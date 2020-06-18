package com.petstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.petstore.model.Pet;
import com.petstore.model.Store;
import com.petstore.repository.PetRepository;
import com.petstore.repository.StoreRepository;

@SpringBootTest
//@Transactional
@Sql(scripts = {"classpath:/db/insert.sql"})
class PetstoreApplicationTests {

	Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	Pet dowry;
	
	@BeforeEach
	public void setUp() {
		
		dowry = new Pet();
		dowry.setColor("Brown");
		dowry.setName("dowry");
		dowry.setType("cat");
		
	}
	
	
	@Test
	void contextLoads() {
		
		assertThat(petRepository).isNotNull();
		assertThat(storeRepository).isNotNull();
	}
	
	@Test
	void savePetsTodb() {
		
		logger.info("Creating a pet object");
		
		
		logger.info("Saving pet Object to db"+dowry);
		
		petRepository.save(dowry);
		
		logger.info("Pet object after saving"+dowry);
		
		Pet savedPet = petRepository.findById(dowry.getId()).get();
		
		assertThat(savedPet).isNotNull();
		assertThat(savedPet.getName()).isEqualTo(dowry.getName());

		
	}
	
	
	
	@Test
	void savePetsToStore() {
		
		logger.info("Creating a store");
		Store store = new Store();
		store.setLocation("Yaba");
		store.setName("Petlovers");
		
		List<Pet> pets = new ArrayList<>();
		
		pets.add(dowry);
	
		store.setPets(pets);
		
		logger.info("Saving store object"+store);
		storeRepository.save(store);
		
		logger.info("Store after saving"+store);
			
	}
	
	@Test
	public void findAll() {
		
		List<Pet> pets = petRepository.findAll();
		
		assertThat(pets.size()).isEqualTo(4);
	}
	
	

}
