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

</head>
<body>
	<section class="section">
		<div class="container">
			<h1 class="title">Welcome to the Billing System</h1>
			<p class="subtitle">
				Just Fill the following form and generate the <strong>Bill
					in pdf format</strong>!
			</p>
			<hr></hr>
			<!-- FORM -->
			<form id="billForm" name="billForm" action="generateBillInPDF"
				target="_blank" method="POST">
				<div class="field">
					<label class="label">Customer Name</label>
					<div class="control">
						<input class="input" type="text" placeholder="Customer Name"
							id="customer" name="customer">
					</div>
				</div>

				<div class="field">
					<label class="label">Customer Email</label>
					<div class="control has-icons-left has-icons-right">
						<input class="input" type="email" placeholder="Email input"
							id="customerEmail" name="customerEmail" value="hello@"> <span
							class="icon is-small is-left"> <i class="fas fa-envelope"></i>
						</span>
					</div>
				</div>

				<div class="field">
					<label class="label">Your Company</label>
					<div class="control has-icons-left has-icons-right">
						<input class="input" type="text" placeholder="Your Company"
							value="Company IT Ltd. - ID:45789052" name="providerCompanyData" id="providerCompanyData"> <span
							class="icon is-small is-left"> <i class="fas fa-user"></i>
						</span>
					</div>

				</div>
				<div class="field">
					<label class="label">Additional Data</label>
					<div class="control">
						<textarea class="textarea" placeholder="Textarea"
							id="adicionalData" name="adicionalData"></textarea>
					</div>
				</div>

				<div class="field">
					<div class="control">
						<label class="checkbox"> <input type="checkbox"> I
							agree to the <a href="#">terms and conditions</a>
						</label>
					</div>
				</div>

				

				<div class="field is-grouped">
					<div class="control">
						<button class="button is-link" type="Submit">Submit</button>
					</div>
					<div class="control">
						<button class="button is-text">Cancel</button>
					</div>
				</div>
			</form>

			<!-- /FORM -->
			<hr></hr>
		</div>
	</section>
</body>
</html>