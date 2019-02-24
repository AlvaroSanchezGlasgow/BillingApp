package com.app.bill.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Controller used to redirect the http call to the initial index view
 * @author asanchga
 *
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private static final String INDEX = "index";
	
	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() throws Exception {
		logger.debug("HomeController - ");
		
		ModelAndView modelAndView = new ModelAndView(INDEX);
		
		return modelAndView;
	}
	
}
