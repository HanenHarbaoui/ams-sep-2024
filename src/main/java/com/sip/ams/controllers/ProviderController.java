package com.sip.ams.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;
import com.sip.ams.services.ProviderService;

import java.util.Comparator;
import java.util.List;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/providers/")

public class ProviderController {

	private final ProviderRepository providerRepository;

	@Autowired
	public ProviderController(ProviderRepository providerRepository) {
		this.providerRepository = providerRepository;
	}

	@Autowired
	ProviderService service;

	@GetMapping("add")
	public String showAddProviderForm(Model model) {
		Provider provider = new Provider();// object dont la valeur des attributs par defaut
		model.addAttribute("provider", provider);
		return "provider/addProvider";
	}

	@PostMapping("add")
	public String addProvider(@Valid Provider provider, BindingResult result) {
		if (result.hasErrors()) {
			return "provider/addProvider";
		}
		service.addProvider(provider);
		return "redirect:list";
	}

	@GetMapping("list")
	// @ResponseBody
	public String listProviders(Model model) {
		List<Provider> res = service.listProvider();
		if (res.size() == 0)
			res = null;
		
res.sort(Comparator.comparing(Provider::getName));
		model.addAttribute("providers", res);

		return "provider/listProviders";

		// List<Provider> lp = (List<Provider>)providerRepository.findAll();
		// System.out.println(lp);

		// return "Nombre de fournisseur = " + lp.size();
	}

	@GetMapping("delete/{id}")
	public String deleteProvider(@PathVariable("id") long id,Model model) {
		
		
		try {
			
			
			
			service.findProviderById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
			service.delete(id);
		
		}
			catch(IllegalArgumentException ex)
			{
				return "provider/500.html";
			}
		
		
		
		
		
		/* long id2 = 100L;

		Provider provider = providerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));

		System.out.println("suite du programme...");

		providerRepository.delete(provider);

		
		 * model.addAttribute("providers", providerRepository.findAll()); 
		 * return  "provider/listProviders";
		 */
		return "redirect:../list";
	}

	@GetMapping("edit/{id}")
	public String showProviderFormToUpdate(@PathVariable("id") long id, Model model) {
		Provider provider = null;
		try {
			
		
		
		provider = service.findProviderById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
		}
		catch(IllegalArgumentException ex)
		{
			return "provider/500.html";
		}
		
		model.addAttribute("provider", provider);

		return "provider/updateProvider";
	}

	@PostMapping("update")
	public String updateProvider(@Valid Provider provider, BindingResult result, Model model) {

		service.addProvider(provider);
		return "redirect:list";

	}
}
