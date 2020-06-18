package com.petstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petstore.model.Store;
import com.petstore.repository.StoreRepository;

@Service("storeService")
public class StoreServiceImpl implements StoreService{

	
	@Autowired
	StoreRepository storeRepository;

	@Override
	public List<Store> findAll() {
		// TODO Auto-generated method stub
		return storeRepository.findAll();
	}

	@Override
	public Store findOne(Integer storeId) {
		// TODO Auto-generated method stub
		return storeRepository.getOne(storeId);
	}

	@Override
	public Store save(Store store) {
		// TODO Auto-generated method stub
		return storeRepository.save(store);
	}
	

	

}
