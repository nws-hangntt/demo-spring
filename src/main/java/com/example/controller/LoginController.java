package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Product;
import com.example.model.User;
import com.example.service.ProductService;
import com.example.service.UserService;

/**
 * The Class LoginController.
 */
@Controller
public class LoginController {
	
	/** The user service. */
	@Autowired
	private UserService userService;

	/** The product service. */
	@Autowired
	private ProductService productService;
	
	/**
	 * Login.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	/**
	 * Registration.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	/**
	 * Creates the new user.
	 *
	 * @param user the user
	 * @param bindingResult the binding result
	 * @return the model and view
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser( Model model, @Valid User user, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		//check user exists
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			result.rejectValue("email", "error.user", "There is already a user registered with the email provided");
		} else {
			if (result.hasErrors()) {
				modelAndView.setViewName("registration");
			} else {
				userService.saveUser(user);
				modelAndView.addObject("successMessage", "User has been registered successfully");
				modelAndView.addObject("user", new User());
				modelAndView.setViewName("redirect:/login");
			}
		}
		return modelAndView;
	}

	/**
	 * Home.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "admin/product", method = RequestMethod.GET)
	public ModelAndView product() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		User user = userService.findUserByEmail(auth.getName());
		
		List<Product> listProduct = productService.findAll();

		modelAndView.addObject("userName",
				"Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("successMessage", "Content Available Only for Users with Admin Role");
		modelAndView.addObject("listProduct", listProduct);
		modelAndView.setViewName("product/index");
		return modelAndView;
	}
	
}
