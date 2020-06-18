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
@Sql(scripts = {"classpath:/db/insert.sql"})
class StoreRepositoryTest {

	Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	Store petLovers;
	
	@BeforeEach
	public void setUp() {
		
		petLovers = new Store();
		petLovers.setLocation("Yaba");
		petLovers.setName("Petlovers");
		
	}
	
	
	@Test
	void contextLoads() {
		
		assertThat(storeRepository).isNotNull();
		assertThat(petRepository).isNotNull();

	}

	@Test
	void savePetsToStore() {
	
		
		logger.info("Saving store object"+petLovers);
		storeRepository.save(petLovers);
		
		logger.info("Store after saving"+petLovers);
		
		
		logger.info("creating pet object database");
		Pet bob =new Pet();
		bob.setColor("gold");
		bob.setName("jane");
		bob.setPetType("rabbit");
		
		logger.info("pet --> {}"+bob);

		
		logger.info("Adding pets to store");
		List<Pet> pets = new ArrayList<>();
		pets.add(bob);
		
		petLovers.setPets(pets);
		
		
		storeRepository.save(petLovers);
		
		Store savedStore = storeRepository.findById(petLovers.getId()).get();
		logger.info("saved store object"+savedStore);
		assertThat(savedStore.getPets()).isNotNull();

			
	}
	

	
	

}
