package com.clone_trello.CT.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.clone_trello.CT.service.LoginService;
import com.clone_trello.CT.service.SignUpService;

@Controller
public class TrelloController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	SignUpService signUpService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String result(@RequestParam(value = "ID", required = false) String uname, Model model) {
		System.out.println("서버");
		System.out.println(loginService.SearchInfo(uname) + "서버");
		if(loginService.SearchInfo(uname) == true) {
			return "result";
		}
		else {
			return "resultFalse";
		}
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String SignUp() {
		return "signUp";
	}
	@RequestMapping(value = "/signUpResult", method = RequestMethod.GET)
	public String SignUp(@RequestParam(value = "id" , required = false)String id,@RequestParam(value = "PW" , required = false)String pw) {
		
		signUpService.InsertInfo(id, pw);
		return "result";
	}
	
}