package com.bozpower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MVCConfiguration   {
	
	

	
	@RequestMapping("/")
	public String view() {

		return "login";
	}
}
