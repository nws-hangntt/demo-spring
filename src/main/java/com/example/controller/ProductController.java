package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Product;
import com.example.model.User;
import com.example.service.ProductService;
import com.example.service.UserService;

/**
 * The Class ProductController.
 */
@Controller
@RequestMapping("admin/product")
public class ProductController {

	/** The product service. */
	@Autowired
	private ProductService productService;
	
	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Creates the product.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public ModelAndView createProduct() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName",
				"Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("product", new Product());
		modelAndView.setViewName("product/add");
		return modelAndView;
	}

	/**
	 * Adds the product.
	 *
	 * @param model the model
	 * @param product the product
	 * @param result the result
	 * @return the model and view
	 */
	@RequestMapping(value = { "/add" }, method = RequestMethod.POST, consumes = { "application/x-www-form-urlencoded" })
	public ModelAndView addProduct( Model model, @Valid @ModelAttribute("product") Product product, BindingResult result) {
		if (result.hasErrors()){
			return new ModelAndView("product/add");
		    }
		    else {
		    	ModelAndView modelAndView = new ModelAndView();
		    	//get profile user current login
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				User user = userService.findUserByEmail(auth.getName());
				
				//set Product Status after create success defaul value = 1
				product.setProductStatus(1);
				
				//save product
				productService.save(product);
				
				modelAndView.addObject("userName",
						"Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
				modelAndView.setViewName("redirect:/admin/product");
				return modelAndView;		    
			}
		
	}
	
	/**
	 * Edits the product.
	 *
	 * @param id the id
	 * @return the model and view
	 */
	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
	public ModelAndView editProduct(@ModelAttribute("id") Integer id) {		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("id", String.valueOf(id)); 
		modelAndView.addObject("product", productService.findOne(id));
		modelAndView.setViewName("product/edit");
		return modelAndView;
	}
	
	/**
	 * Update product.
	 *
	 * @param model the model
	 * @param product the product
	 * @param result the result
	 * @return the model and view
	 */
	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.POST, consumes = { "application/x-www-form-urlencoded" })
	public ModelAndView updateProduct(Model model, @Valid @ModelAttribute("product") Product product, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("product/edit");
		} else {
			productService.save(product);
			return new ModelAndView("redirect:/admin/product");
		}
	}
	
	/**
	 * Delete product.
	 *
	 * @param id the id
	 * @return the model and view
	 */
	@RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.GET)
	public ModelAndView deleteProduct(@ModelAttribute("id") Integer id) {		
		productService.delete(id);
		return new ModelAndView("redirect:/admin/product");
	}
}
