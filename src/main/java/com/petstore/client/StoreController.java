/**
 * 
 */
package com.petstore.client;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petstore.model.Pet;
import com.petstore.model.Store;
import com.petstore.service.StoreService;

/**
 * @author user
 *
 */

@RestController
public class StoreController {
	
	
	Logger log= Logger.getLogger(getClass().getName());
	
	@Autowired @Qualifier("storeService")
	StoreService storeServiceImpl;
	
	
	@GetMapping("/stores")
	public List<Store> returnAllStores(){
	
		return storeServiceImpl.findAll();
	}
	
	@PostMapping("/store")
	public Store savePet(@RequestBody Store store) {
		
		store = storeServiceImpl.save(store);
		
		return store;
		
	}
	
	
	@PostMapping("/storePets{storeId}")
	public Store addPetToStore(@RequestBody Pet pet, @RequestParam("storeId") Integer storeId) {
		
		log.info("New request Pet --->{"+pet+"}"+"\n to store Id --> "+storeId);
		
		Store savedStore = storeServiceImpl.findOne(storeId);
		
		List<Pet> petList = new ArrayList<>();
		petList.add(pet);
		
		if(!(savedStore == null)) {
			
			savedStore.setPets(petList);
		}
		
		return storeServiceImpl.save(savedStore);
		
	}
	
	
}
