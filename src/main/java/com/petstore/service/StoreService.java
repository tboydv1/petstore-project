package com.petstore.service;

import java.util.List;

import com.petstore.model.Store;

public interface StoreService {
 
	public List<Store> findAll();
	
	public Store findOne(Integer storeId);
	
	public Store save(Store store);
	
}
