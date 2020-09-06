/**
 * 
 */
package com.petstore.client;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.petstore.client.dto.PetDto;
import com.petstore.exception.PetNotFoundException;
import com.petstore.service.pet.PetMapper;
import com.petstore.util.PatchMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petstore.model.Pet;
import com.petstore.service.pet.PetService;


/**
 * @author user
 *
 */

@RestController
@RequestMapping("/api")
public class PetController {

	@Autowired
	PetService petServiceImpl;

	@Autowired
	PatchMapper patchMapperUtil;

	@Autowired
	PetMapper petMapper;

	Logger log = Logger.getLogger(getClass().getName());
	
	
	@GetMapping(path = "/pets")
	public ResponseEntity<?> findAllPets(){
	
		List<Pet> petList = petServiceImpl.findAll();

		return new ResponseEntity<>(petList, HttpStatus.OK);
	}
	
	@PostMapping(path = "/pet", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> savePet(@RequestBody PetDto petDto) {

		log.info("Pet object to be saved --> {}"+petDto);
		Pet pet = petServiceImpl.save(petDto);

		return new ResponseEntity<>(pet, HttpStatus.CREATED);
		
	}

	


}


