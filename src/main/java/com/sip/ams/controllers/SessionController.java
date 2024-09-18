package com.sip.ams.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sessions")
public class SessionController {
	
 @RequestMapping("list")
 public String ListSession()
	{
	 	
	 	//String names[] = {"OCA","OCP","FULLSTACK"};
	 	//model.addAttribute("names", names);
	 	
		return "session/listSession";
	}
 @RequestMapping("addSession")

	public String addSession()
	{
		return "addSession";
	}
 }