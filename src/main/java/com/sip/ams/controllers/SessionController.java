package com.sip.ams.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.ams.entities.Session;

@Controller
@RequestMapping("/sessions")
public class SessionController {
	public static List<Session> sessions;
	static {
		Session s1 = new Session("OCA", "oca.png",
				"C'est une formation permettant aux stagiaires d'avoir un niveau confirmé en tant que développeur Java, ce niveau vous permet de passer l'examen Oracle 1Z0-808 et de se plonger dans les framework tel que Spring. Plusieurs thématiques seront traitées dans cette formation tel que les API coeur de Java, les exceptions, le polymorphisme et l'encapsulation, l'expression lambda et la programmation fonctionnelle ! c'est par ici qu'il faut démarrer :)");
		Session s2 = new Session("OCP", "ocp.png",
				"Ce niveau professionnel permet d'avoir  les compétences nécessaires pour être un développer senior en Java et capable de passer les certifications (Java 8) 1Z0-809, (Java 11)  1Z0-819 et (Java 17)  1Z0-829, Pour démarrer ce niveau, il faut avoir la formation OCA 1Z0-808. Plusieurs API avancés seront traités tel que les gestions des threads avec l'api Executors, Les designs Patterns, JDBC, la programmation modulaires et la programmation fonctionnelle via lambda et l'api Stream. les IO stream ainsi que les exceptions feront aussi partie du plan de formation OCP :)");
		Session s3 = new Session("FullStack Spring Angular", "fullstack.png",
				" C'est une formation complète 100% pratique permettant de former des stagiaires opérationnels et fonctionnels selon les requis du monde professionel visant à les former sur les frameworks les plus demandés tel que spring boot et angular ainsi que les outils devops et l'agilité. Plus que 4 projets seront réalisés sous la supervision de coach expert du domaine permettant de faire une synthèse des apprentissages des stagiares durant les camps de cours ce qui renforce la capacité des nos étudiants à monter en compétences en peu de temps et cela grace au cours, projets et supervision :)");
		sessions = new ArrayList<>();
		sessions.add(s1);
		sessions.add(s2);
		sessions.add(s3);

	}

	@GetMapping("/list")
	public String listSession(Model model) {

		model.addAttribute("sessions", sessions);
		return "sessions/listSessions";
	}

	@GetMapping("/showAddSessionsForm")

	public String addSession(Model model) {
		Session temp= new Session ();
		model.addAttribute("temp",temp);
		return "sessions/addSession";
	}
	
	@PostMapping("/saveSession")
	
	
	public String saveSession(Session temp) {
		temp.setLogo("oca.png");
		sessions.add(temp);
	return "redirect:list";
	
}
}