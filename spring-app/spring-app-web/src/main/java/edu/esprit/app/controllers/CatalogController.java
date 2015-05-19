package edu.esprit.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.esprit.app.entities.Product;
import edu.esprit.app.service.ICatalogService;

@Controller
public class CatalogController {

	@Autowired
	ICatalogService service;

	@RequestMapping(value = "/index")
	public String index(Model model) {

		model.addAttribute("product", new Product());
		model.addAttribute("products", service.getAllProducts());
		return "products";
	}

	@RequestMapping(value = "/addProduct")
	public String addProduct(@Valid Product product, BindingResult br, Model model) {

		if (br.hasErrors()) {
			model.addAttribute("products", service.getAllProducts());
			return "products";
		}

		service.addProduct(product);
		model.addAttribute("product", new Product());
		model.addAttribute("products", service.getAllProducts());
		return "products";
	}
	
	@RequestMapping(value = "/deleteProduct")
	public String deleteProduct(@RequestParam("prod") String prodRef, Model model) {

		service.deleteProduct(prodRef);
		model.addAttribute("product", new Product());
		model.addAttribute("products", service.getAllProducts());
		return "products";
	}
	
	@RequestMapping(value = "/selectForUpdate")
	public String selectForUpdate(@RequestParam("selectedProdRef") String selectedProdRef, Model model ){
		
		Product p = service.getProductByReference(selectedProdRef);
		model.addAttribute("product", p);
		model.addAttribute("products", service.getAllProducts());
		return "products";
	}
	
}
