/**
 * 
 */
package com.petstore.client;


import com.petstore.service.exception.StoreNotFoundException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petstore.model.Pet;
import com.petstore.model.Store;
import com.petstore.service.store.StoreService;

/**
 * @author user
 *
 */

@RestController
@RequestMapping("/api")
public class StoreController {
	
	
	Logger log= Logger.getLogger(getClass().getName());
	
	@Autowired
	StoreService storeServiceImpl;
	
	
	@GetMapping("/stores")
	public ResponseEntity<?> findAllStores(){
	
		return ResponseEntity.ok().body(storeServiceImpl.findAll());
	}

	/**
	 * @param store
	 * @return
	 */
	@PostMapping("/store")
	public ResponseEntity<Store> CreateStore(@RequestBody Store store) {
		
		store = storeServiceImpl.save(store);
		
		return new ResponseEntity<>(store, HttpStatus.CREATED);
	}

	/**
	 *
	 * @param pet
	 * @param storeId
	 * @return
	 */
	@PostMapping("/storePets{storeId}")
	public ResponseEntity<?> addPetToStore(@RequestBody Pet pet, @RequestParam("storeId") Integer storeId) {

		Store store = null;
		try {

			store = storeServiceImpl.addPets(pet, storeId);

		} catch (StoreNotFoundException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok().body(store);

	}
	
	
}
