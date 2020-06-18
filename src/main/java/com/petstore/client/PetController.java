/**
 * 
 */
package com.petstore.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petstore.model.Pet;
import com.petstore.service.PetService;

/**
 * @author user
 *
 */

@RestController
public class PetController {

	@Autowired @Qualifier("petservice")
	PetService petServiceImpl;
	
	
	@GetMapping("/pets")
	public List<Pet> sayHello(){
	
		return petServiceImpl.findAll();
	}
}


