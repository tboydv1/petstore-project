/**
 * 
 */
package com.petstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.petstore.model.Pet;
import com.petstore.repository.PetRepository;

/**
 * @author user
 *
 */
@SpringBootTest
@Sql(scripts = {"classpath:/db/insert.sql"})
class PetRepoisotyTest {
	
	
	@Autowired
	PetRepository petRepository;
	
	Logger logger = Logger.getLogger(getClass().getName());
	

	/**
	 * @throws java.lang.Exception
	 */
	
	Pet dowry;
	
	@BeforeEach
	void setUp() throws Exception {
		
		
		dowry = new Pet();
		dowry.setColor("Brown");
		dowry.setName("dowry");
		dowry.setPetType("cat");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void contextLoads() {
		
		assertThat(petRepository).isNotNull();
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
	public void findAll() {
		
		List<Pet> pets = petRepository.findAll();
		
		assertThat(pets.size()).isEqualTo(4);
	}
	
}
