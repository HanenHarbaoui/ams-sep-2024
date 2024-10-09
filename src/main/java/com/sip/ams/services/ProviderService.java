package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;

@Service
public class ProviderService {
	@Autowired
	ProviderRepository providerRepository;
	
	public Provider addProvider(Provider provider) {
		 return providerRepository.save(provider);
	
	
	}
	
	public List <Provider>listProvider()
	{
		return (List<Provider>)providerRepository.findAll();
	}

	public void delete(long id) {
		providerRepository.deleteById(id);
		
	}
	public Optional <Provider> findProviderById(long id)
	{
		return providerRepository.findById(id);
	}

	

}
