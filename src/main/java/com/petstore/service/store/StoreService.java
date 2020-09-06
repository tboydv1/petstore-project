package com.petstore.service.store;

import java.util.List;
import java.util.Optional;

import com.petstore.model.Pet;
import com.petstore.model.Store;
import com.petstore.service.exception.StoreNotFoundException;

public interface StoreService {

	Store save(Store store);

	Optional<Store> findById(Integer storeId);

	Store update(Store store);

	void delete(Integer storeId);

	List<Store> findAll();

	Store addPets(Pet pet, Integer storeId) throws StoreNotFoundException;

	List<Pet> findStorePets(Integer storeId) throws StoreNotFoundException;
}
