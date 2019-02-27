package com.app.bill.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Getter
@Setter
@NoArgsConstructor
public class BillDTO {
	
	//Bill ID
	private int Id;
	
	//Provided Company Data
	private String providerCompany;
	private String AddressLine1;
	private String AddressLine2;
	private String customerEmail;
	private String providerCompanyData;
	private String adicionalData;
	
	
}
