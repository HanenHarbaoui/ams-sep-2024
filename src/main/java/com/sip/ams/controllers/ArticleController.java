package com.sip.ams.controllers;

import jakarta.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;

import com.sip.ams.services.ArticleService;
import com.sip.ams.services.ProviderService;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/article/")
public class ArticleController {

	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/uploadArticles";

	private final ArticleService articleService;
	private final ProviderService providerService;

	@Autowired
	public ArticleController(ArticleService articleService, ProviderService providerService) {
		this.articleService = articleService;
		this.providerService = providerService;
	}

	@GetMapping("list")
	public String listArticles(Model model) {
		List<Article> articles = articleService.listArticle();
		if (articles.size() == 0)
			articles = null;

		model.addAttribute("articles", articles);
		return "article/listArticles";
	}

	@GetMapping("add")
	public String showAddArticleForm(Article article ,Model model) {
		model.addAttribute("providers", providerService.listProvider());
		model.addAttribute("article", new Article());
		return "article/addArticle";
	}

	@PostMapping("add")
	// @ResponseBody
	public String addArticle(@Valid Article article, BindingResult result,
			@RequestParam(name = "providerId", required = false) Long p,
			@RequestParam("files") MultipartFile[] files) {
		Provider provider = providerService.findProviderById(p)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + p));
		article.setProvider(provider);
		// upload image

		MultipartFile file = files[0];
		
		long time =new Date().getTime();
		Path fileNameAndPath = Paths.get(uploadDirectory, " "+time+file.getOriginalFilename());

		StringBuilder fileName = new StringBuilder();

		fileName.append(""+time+file.getOriginalFilename());

		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		article.setPicture(fileName.toString());

		// fin upload

		articleService.saveArticle(article);
		return "redirect:list";
		// return article.getLabel() + " " +article.getPrice() + " " +

	}

	@GetMapping("delete/{id}")
	public String deleteArticle(@PathVariable("id") long id, Model model) {
		articleService.findArticleById(id).orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
		articleService.delete(id);

		return "redirect:../list";
	}

	@GetMapping("edit/{id}")
	public String showArticleFormToUpdate(@PathVariable("id") long id, Model model) {
		try {
			Article article = articleService.findArticleById(id)
					.orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
			model.addAttribute("article", article);
			model.addAttribute("providers", providerService.listProvider()); // Récupérer tous les fournisseurs
			model.addAttribute("idProvider", article.getProvider().getId()); // Ajouter l'id du fournisseur de l'article
		} catch (IllegalArgumentException ex) {
			return "article/500.html";
		}

		return "article/updateArticle";
	}

	@PostMapping("edit")
	public String updateArticle(@PathVariable("id") long id ,@Valid Article article, BindingResult result, Model model,
			@RequestParam(name = "providerId", required = false) Long p) {
		if (result.hasErrors()) {
			article.setId(id);

			return "article/updateArticle";
		}
		Provider provider = providerService.findProviderById(p)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + p));
		article.setProvider(provider);
		articleService.saveArticle(article);
		model.addAttribute("articles", articleService.findArticleById(id));
		return "redirect:list";
	}

	@GetMapping("show/{id}")
	public String showArticleDetails(@PathVariable("id") long id, Model model)
	{
	Article article = articleService.findArticleById(id)
	.orElseThrow(()->new IllegalArgumentException("Invalid providerId:" + id));
	model.addAttribute("article", article);
	return "article/showArticle";
	}
}