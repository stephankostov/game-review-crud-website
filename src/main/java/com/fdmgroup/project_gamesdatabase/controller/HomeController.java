package com.fdmgroup.project_gamesdatabase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"", "/home", "Home" })
	public String home() {
		return "home.jsp";
	}

}
