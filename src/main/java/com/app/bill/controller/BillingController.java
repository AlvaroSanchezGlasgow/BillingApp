package com.app.bill.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.bill.util.GeneratePDF;


@Controller
public class BillingController {
	
	private static final Logger logger = LoggerFactory.getLogger(BillingController.class);

	private static final String INDEX = "index";
	
	@PostMapping(value = "/generateBillInPDF", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public void generateBill(@RequestParam(value="customer") String sCustomerData,@RequestParam(value="customerEmail") String sCustomerEmail,
			@RequestParam(value="providerCompanyData") String sProviderCompanyData, @RequestParam(value="adicionalData") String sAdicionalData,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
	
		GeneratePDF bill = new GeneratePDF();
		bill.generateBillingPDF(response, sCustomerData,sProviderCompanyData);
		
		
	}

}
