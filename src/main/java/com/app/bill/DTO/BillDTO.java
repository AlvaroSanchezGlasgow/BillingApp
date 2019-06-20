package com.app.bill.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class BillDTO {
	
	//Bill ID
	 
	 //@Size(min=13,max=13)
	@NotNull
	@NotEmpty
	private int Id;
	
	//Provided Company Data
	@NotNull
	@NotEmpty
	 @Size(min=2, message="Provider Company should have atleast 2 characters")
	private String providerCompany;
	
	@NotNull
	@NotEmpty
	 @Size(max=10, message="Address should have less than 10 characters")
	private String AddressLine1;
	
	@NotNull
	@NotEmpty
	private String AddressLine2;
	
	@NotNull
	@NotEmpty
	private String providerEmail;
	
	@NotNull
	@NotEmpty
	private String customerCompanyData;
	
	@NotNull
	@NotEmpty
	private String adicionalCustomerData;
	
	//Ammount Data	
	private int ammount0;
	private String description0;
	private double unitPrice0;
	private double total0;
	
	private int ammount1;
	private String description1;
	private double unitPrice1;
	private double total1;
	
	private int ammount2;
	private String description2;
	private double unitPrice2;
	private double total2;
	
	private int ammount3;
	private String description3;
	private double unitPrice3;
	private double total3;
	
	private int ammount4;
	private String description4;
	private double unitPrice4;
	private double total4;
	
	private int ammount5;
	private String description5;
	private double unitPrice5;
	private double total5;
	
	private int ammount6;
	private String description6;
	private double unitPrice6;
	private double total6;
	
	private int ammount7;
	private String description7;
	private double unitPrice7;
	private double total7;
	
	private int ammount8;
	private String description8;
	private double unitPrice8;
	private double total8;
	
	private int ammount9;
	private String description9;
	private double unitPrice9;
	private double total9;
	
	private int ammount10;
	private String description10;
	private double unitPrice10;
	private double total10;
	
	//Totals
	private double subTotal;
	private int vatPercentage;
	private double vatAmmount;
	private double total;
	
	
}
