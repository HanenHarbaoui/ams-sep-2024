package com.sip.ams.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SessionController {
	
 @RequestMapping("listSession")

	public String ListSession(Model model)
	{
	 	
	 	String names[] = {"OCA","OCP","FULLSTACK"};
	 	model.addAttribute("names", names);
	 	
		return "listSession";
	}
 @RequestMapping("addSession")

	public String addSession()
	{
		return "addSession";
	}
 }