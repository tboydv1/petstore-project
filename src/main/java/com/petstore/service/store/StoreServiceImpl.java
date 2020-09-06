package com.petstore.service.store;

import java.util.List;
import java.util.Optional;

import com.petstore.model.Pet;
import com.petstore.service.exception.StoreNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petstore.model.Store;
import com.petstore.repository.StoreRepository;

@Service
public class StoreServiceImpl implements StoreService{


	@Autowired
	StoreRepository storeRepository;

	@Override
	public Store save(Store store) {
		return storeRepository.save(store);
	}

	@Override
	public Optional<Store> findById(Integer storeId) {
		return storeRepository.findById(storeId);
	}

	@Override
	public Store update(Store store) {
		return storeRepository.save(store);
	}

	@Override
	public void delete(Integer storeId) {
		storeRepository.deleteById(storeId);
	}

	@Override
	public List<Store> findAll() {
		return storeRepository.findAll();
	}

	@Override
	public Store addPets(Pet pet,  Integer storeId) throws NullPointerException, StoreNotFoundException {


		//validate that pet is not null
		if(pet == null){
			throw new NullPointerException("Pet Object Should not be null");
		}

		//check that store exxisst
		Optional<Store> result = storeRepository.findById(storeId);

		if(result.isPresent()){

			Store savedStore = result.get();
			pet.setPetStore(savedStore);
			savedStore.addPet(pet);
			return storeRepository.save(savedStore);
		}
		else{
			throw new StoreNotFoundException("Store not present in the database");
		}

	}

	@Override
	public List<Pet> findStorePets(Integer storeId) throws StoreNotFoundException {

		//find store
		Optional<Store> result = storeRepository.findById(storeId);


		if(result.isPresent()){

			Store savedStore = result.get();

			return savedStore.getPets();

		}else{
			throw new StoreNotFoundException("Store does not exist");
		}

	}
	

}
