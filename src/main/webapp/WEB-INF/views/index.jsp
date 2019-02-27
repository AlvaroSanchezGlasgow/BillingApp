<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Billing App - Index</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
<script defer
	src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
	
	<!-- JQUERY -->
	

	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

	

</head>
<body>
	<section class="section">
		<div class="container">
			<h1 class="title">Invoizaitor!!</h1>
			<p class="subtitle">
				The easier and quicker <strong>Billing generator in pdf
					format</strong>!
			</p>
			<hr></hr>
			<!-- FORM -->
			<form id="billForm" name="billForm" action="generateBillInPDF"
				target="_blank" method="POST">
				<div class="columns">
					<div class="column">
						<div class="field">
							<label class="label">Your Company</label>
							<div class="control">
								<input class="input" type="text" placeholder="Your Company Name"
									id="providerCompany" name="providerCompany" maxlength="50"
									required>
							</div>
						</div>

						<div class="field">
							<div class="control">
								<input class="input" type="text" placeholder="Address Line 1"
									id="AddressLine1" name="AddressLine1" maxlength="50" required>
							</div>
						</div>

						<div class="field">
							<div class="control">
								<input class="input" type="text" placeholder="Address Line 2"
									id="AddressLine2" name="AddressLine2" maxlength="50" required>
							</div>
						</div>

						<div class="field">
							<div class="control has-icons-left">
								<input class="input" type="email" placeholder="Your Email"
									id="providerEmail" name="providerEmail" value="hello@">
								<span class="icon is-small is-left"> <i
									class="fas fa-envelope"></i>
								</span>
							</div>
						</div>

					</div>
					<div class="column">
						<div class="field">
							<label class="label">Customer</label>
							<div class="control has-icons-left has-icons-right">
								<input class="input" type="text" placeholder="Customer Data"
									value="Company IT Ltd. - ID:45789052"
									name="customerCompanyData" id="customerCompanyData"> <span
									class="icon is-small is-left"> <i class="fas fa-user"></i>
								</span>
							</div>

						</div>
						<div class="field">
							<label class="label">Additional Customer Data</label>
							<div class="control">
								<textarea class="textarea" placeholder="Att: Ms. Jane Doe"
									id="adicionalCustomerData" name="adicionalCustomerData"></textarea>
							</div>
						</div>
					</div>
				</div>

				<hr></hr>

				<table class="table is-striped table is-fullwidth table is-bordered">
					<thead>
						<tr>
							<th>Ammount</th>
							<th>Description</th>
							<th>Unit Price</th>
							<th>Total</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input class="input" type="number"
								placeholder="Ammount 1" id="ammount1" name="ammount1" /></td>
							<td><input class="input" type="text"
								placeholder="Description 1" id="description1"
								name="description1" /></td>
							<td><input class="input" type="number"
								placeholder="Unit Price 1" id="unitPrice1" name="unitPrice1" /></td>
							<td><input class="input" type="number" placeholder="Total 1"
								id="total1" name="total1" value="" readonly /></td>
						</tr>
						<tr>
							<td><input class="input" type="number"
								placeholder="Ammount 2" id="ammount2" name="ammount2" /></td>
							<td><input class="input" type="text"
								placeholder="Description 2" id="description2"
								name="description2" /></td>
							<td><input class="input" type="number"
								placeholder="Unit Price 2" id="unitPrice2" name="unitPrice2" /></td>
							<td><input class="input" type="number" placeholder="Total 2"
								id="total2" name="total2" value="" readonly /></td>
						</tr>
						<tr>
							<td><input class="input" type="number"
								placeholder="Ammount 3" id="ammount3" name="ammount3" /></td>
							<td><input class="input" type="text"
								placeholder="Description 3" id="description3"
								name="description3" /></td>
							<td><input class="input" type="number"
								placeholder="Unit Price 3" id="unitPrice3" name="unitPrice3" /></td>
							<td><input class="input" type="number" placeholder="Total 3"
								id="total3" name="total3" value="" readonly /></td>
						</tr>
						<tr>
							<td><input class="input" type="number"
								placeholder="Ammount 4" id="ammount4" name="ammount4" /></td>
							<td><input class="input" type="text"
								placeholder="Description 4" id="description4"
								name="description4" /></td>
							<td><input class="input" type="number"
								placeholder="Unit Price 4" id="unitPrice4" name="unitPrice4" /></td>
							<td><input class="input" type="number" placeholder="Total 4"
								id="total4" name="total4" value="" readonly /></td>
						</tr>
						<tr>
							<td><input class="input" type="number"
								placeholder="Ammount 5" id="ammount5" name="ammount5" /></td>
							<td><input class="input" type="text"
								placeholder="Description 5" id="description5"
								name="description5" /></td>
							<td><input class="input" type="number"
								placeholder="Unit Price 5" id="unitPrice5" name="unitPrice5" /></td>
							<td><input class="input" type="number" placeholder="Total 5"
								id="total5" name="total5" value="" readonly /></td>
						</tr>
						<tr>
							<td><input class="input" type="number"
								placeholder="Ammount 6" id="ammount6" name="ammount6" /></td>
							<td><input class="input" type="text"
								placeholder="Description 6" id="description6"
								name="description6" /></td>
							<td><input class="input" type="number"
								placeholder="Unit Price 6" id="unitPrice6" name="unitPrice61" /></td>
							<td><input class="input" type="number" placeholder="Total 6"
								id="total6" name="total6" value="" readonly /></td>
						</tr>

					</tbody>

					<tfoot>
						<tr>
							<td></td>
							<td>VAT</td>
							<td><input type="number" placeholder="%" id="vatPercentage"
								name="vatPercentage" /></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>TOTAL</td>
							<td></td>
						</tr>
					</tfoot>
				</table>





				<div class="field is-grouped is-grouped-centered">
					<p class="control">
					<input class="button is-primary is-active" type="submit" click="javascript:$(#billForm).submit();" value="Submit input">
					<input class="button is-warning is-active" type="reset" value="Reset input">
					
					</p>
				</div>

			</form>

			<!-- /FORM -->
			<hr></hr>
		</div>
	</section>
</body>
</html>