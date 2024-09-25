package com.sip.ams.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.ams.AmsApplication;
import com.sip.ams.entities.Candidate;

@Controller
@RequestMapping("/Candidate")
public class CandidateController {

@GetMapping("/list")
public String listCandidate(Model model)
{
model.addAttribute("list", AmsApplication.lc);
return "candidate/list";
}

@GetMapping("/add")
public String formAddCandidate(Model model)
{
model.addAttribute("candidat", new Candidate());
return "candidate/add";
}

@PostMapping("/add")
//@ResponseBody
public String PostAddCandidate(Model model, Candidate candidat)
{
//model.addAttribute("candidat", new Candidate());

AmsApplication.lc.add(candidat);
//System.out.println(AmsApplication.lc);
//return candidat.getName();

model.addAttribute("list", AmsApplication.lc);
return "candidate/list";

//return "redirect:list";
}

@GetMapping("/show/{id}")
//@ResponseBody
public String showCandidate(@PathVariable("id") int idC, Model
model)
{

Candidate candidate = AmsApplication.lc.get(idC);
//return c.getName() + " : " +c.getEmail();
model.addAttribute("candidate",candidate);
return "candidate/show";
}

}