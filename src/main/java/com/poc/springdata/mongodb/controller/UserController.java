package com.poc.springdata.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.poc.springdata.mongodb.documents.User;
import com.poc.springdata.mongodb.repositories.UserRepository;

/**
 * @author Ran.ga.na.than
 * 
 */
@Controller
public class UserController {

	@Autowired
	private UserRepository userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUsers(ModelMap model) {
		model.addAttribute("personList", userService.findUsers());
		return "output";
	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public View saveUser(@ModelAttribute User user, ModelMap model) {
		if (StringUtils.hasText(user.getId().toString())) {
			userService.update(user);
		} else {
			userService.save(user);
		}
		return new RedirectView("/mongodb/users");
	}

	@RequestMapping(value = "/user/remove", method = RequestMethod.GET)
	public View removeUser(@ModelAttribute User user, ModelMap model) {
		userService.remove(user);
		return new RedirectView("/mongodb/users");
	}

}
