package com.sip.ams.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/etudiant")
public class EtudiantController {
	
 @RequestMapping("list")
 //@ResponseBody
	public String ListEtudiants(Model model)
	{
	 	int total =15;
	 	String trainer = "Amine";
	 	model.addAttribute("totalStudent", total);
	 	model.addAttribute("trainerStudent", trainer);
		return "Etudiant/listEtudiant";
	}
 @RequestMapping("add")
 //@ResponseBody
	public String addEtudiants()
	{
		return "Etudiant/addEtudiant";
	}
 @RequestMapping("SaveEtudiant")
 @ResponseBody
	public String SaveEtudiant(@RequestParam("nomEtudiant")String nom)
	{
		return "vous ètes:" +nom;
	}
}
