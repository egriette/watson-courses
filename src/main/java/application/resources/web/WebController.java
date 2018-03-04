package application.resources.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/")
	public String index() {
		return "index.html";
	}

	// @GetMapping("/error")
	// public String error() {
	// return "error";
	// }
	//
	// @GetMapping("/chat")
	// public String hello(Model model/*
	// * ,
	// *
	// * @RequestParam(value = "name", required = false, defaultValue = "World")
	// * String name
	// */) {
	// model.addAttribute("user", "etienne");
	// return "conversation";
	// }

}
