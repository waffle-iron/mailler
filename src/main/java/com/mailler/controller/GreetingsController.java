package com.mailler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingsController {

	@Autowired
	private SqsQueueSender sqsSender;
	
	@RequestMapping(value = "/greetings", method = RequestMethod.GET)
	public String greeting(@RequestParam(value = "name", required = false) String name, Model model) {
		model.addAttribute("name", name);
		sqsSender.send(name);
		
		return "greeting";
	}
	
}
