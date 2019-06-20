
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

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>



</head>
<body>
	<section class="section">
	
			<h1 class="title">Invoizaitor!!</h1>
			<p class="subtitle">
				The easier and quicker <strong>Billing generator in pdf
					format</strong>!
			</p>
			<hr></hr>
			
			<!-- FORM -->
			<form class="has-background-light" id="billForm" name="billForm" action="generateBillInPDF"
				target="_blank" method="POST" style="border:solid 1px;border-radius:15px;">
				<div class="container"> 
				<br></br>
				<div class="columns">
					<div class="column">
					
						<div class="field">
							<label class="label">Your Company</label>
							<div class="control">
								<input class="input is-info" type="text" placeholder="Your Company Name"
									id="providerCompany" name="providerCompany" maxlength="50"
									required>
							</div>
						</div>

						<div class="field">
							<div class="control">
								<input class="input is-info" type="text" placeholder="Address Line 1"
									id="AddressLine1" name="AddressLine1" maxlength="50" required>
							</div>
						</div>

						<div class="field">
							<div class="control">
								<input class="input is-info" type="text" placeholder="Address Line 2"
									id="AddressLine2" name="AddressLine2" maxlength="50" required>
							</div>
						</div>

						<div class="field">
							<div class="control has-icons-left">
								<input class="input is-info" type="email" placeholder="Your Email"
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
								<input class="input is-info" type="text" placeholder="Customer Data"
									value="Company IT Ltd. - ID:45789052"
									name="customerCompanyData" id="customerCompanyData"> <span
									class="icon is-small is-left"> <i class="fas fa-user"></i>
								</span>
							</div>

						</div>
						<div class="field">
							<label class="label">Additional Customer Data</label>
							<div class="control">
								<textarea class="textarea is-info" placeholder="Att: Ms. Jane Doe"
									id="adicionalCustomerData" name="adicionalCustomerData"></textarea>
							</div>
						</div>
					</div>
				</div>
</div>
				<br></br>

				
				<div class="container">
				<a href="javascript:addRow();" class="button is-dark">Add Row</a>

				
				<div id="myModal" class="notification is-danger"
					style="display: none;">
					
					Max <strong>10 rows</strong>
				</div>
				<div class="columns">
				
				<div class="column">
<br></br>
				<table id="tableBilling" class="table is-bordered is-striped is-narrow is-hoverable is-fullwidth">
					<thead>
						<tr>
							<th></th>
							<th>Ammount</th>
							<th>Description</th>
							<th>Unit Price</th>
							<th>Total</th>
						</tr>
					</thead>
					<tbody id="mainTable">
						<tr>
							<td></td>
							<td><input class="input is-primary" type="number" placeholder="Ammount"
								id="ammount0" name="ammount0"
								oninput="javascript:calculateTotals();" /></td>
							<td><input class="input is-primary" type="text"
								placeholder="Description" id="description0" name="description0" /></td>
							<td><input class="input is-primary" type="number"
								placeholder="Unit Price" id="unitPrice0" name="unitPrice0"
								oninput="javascript:calculateTotals();" /></td>
							<td><input class="input is-primary" type="number" placeholder="Total"
								id="total0" name="total0" value="" readonly /></td>
						</tr>
					</tbody>

					<tfoot>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td>SubTotal</td>
							<td><input class="input is-primary" type="number"
								placeholder="SubTotal" id="subTotal" name="subTotal" readonly /></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>VAT %</td>
							<td><input class="input is-primary" type="number" placeholder="%"
								id="vatPercentage" name="vatPercentage"
								oninput="javascript:calculateTotals();" /></td>
							<td><input class="input is-primary" placeholder="VAT" type="number" id="vatAmmount"
								name="vatAmmount" readonly /></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td>TOTAL</td>
							<td><input class="input is-primary" type="number" placeholder="TOTAL"
								id="total" name="total" readonly /></td>
						</tr>
					</tfoot>
				</table>
</div>
</div>
</div>
<br></br>
				<div class="field is-grouped is-grouped-centered">
					<p class="control">
						<input class="button is-primary is-active" type="submit"
							click="javascript:$(#billForm).submit();" value="Submit input">
						<input class="button is-warning is-active" type="reset"
							value="Reset input">

					</p>
					<br></br>
				</div>

			</form>

			<!-- /FORM -->
			<hr></hr>
		<!-- </div> -->
	</section>


	<script>
		function deleteRow() {

			$("#myModal").hide();
			
			var rowNumber = $("#mainTable tr").length;
			$("#row" + (rowNumber - 1)).remove();
			
			

		}

		function addRow() {

			var counter = $("#mainTable tr").length;
			if ((counter - 1) < 10) {

				$("#myModal").hide();
				var counter = $("#mainTable tr").length;
				$("#mainTable")
						.append(

								"<tr id=\"row"+counter+"\" name=\"row"+counter+"\">"
										+ "<td><a href=\"javascript:deleteRow("
										+ ");\"  class=\"button is-text\">Delete Row</a></td>"
										+ "<td><input class=\"input is-primary\" type=\"number\" placeholder=\"Ammount\""
										+ "id=\"ammount"
										+ counter
										+ "\" name=\"ammount"
										+ counter
										+ "\" oninput=\"javascript:calculateTotals();\"/></td>"
										+ "<td><input class=\"input is-primary\" type=\"text\""
			+ "placeholder=\"Description\" id=\"description"+counter+"\""
			+ "name=\"description"+counter+"\" /></td>"
										+ "<td><input class=\"input is-primary\" type=\"number\""
										+ "placeholder=\"Unit Price\" id=\"unitPrice"
										+ counter
										+ "\" name=\"unitPrice"
										+ counter
										+ "\" oninput=\"javascript:calculateTotals();\"/></td>"
										+ "<td><input class=\"input is-primary\" type=\"number\" placeholder=\"Total\""
			+ "id=\"total"+counter+"\" name=\"total"+counter+"\" value=\"\" readonly /></td>"
										+ "</tr>"

						);
			} else {
				$("#myModal").show();
			}

		}

		function calculateTotals() {

			var item = $("#mainTable tr").length;

			var i;

			var counter = 0;

			$('#mainTable tr').each(
					function(row) {

						if ($("#ammount" + row).val() == "") {
							$("#ammount" + row).val("0");
						}

						if ($("#unitPrice" + row).val() == "") {
							$("#unitPrice" + row).val("0");
						}

						$("#total" + row).val(
								parseInt($("#ammount" + row).val()
										* $("#unitPrice" + row).val()));

						counter = counter + parseInt($("#total" + row).val());
					});

			$("#subTotal").val(counter);

			if ($("#subTotal").val() == "") {
				$("#subTotal").val("0");
			}

			if ($("#vatPercentage").val() == "") {
				$("#vatPercentage").val("0");
			}

			$("#vatAmmount").val(
					parseInt($("#subTotal").val())
							* (parseInt($("#vatPercentage").val()) / 100));

			if ($("#vatAmmount").val() == "") {
				$("#vatAmmount").val("0");
			}
			$("#total").val(
					parseInt($("#subTotal").val())
							+ parseFloat($("#vatAmmount").val()));
		}
	</script>


</body>
</html>

