<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Billing App - Error</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>

</head>
<body>


<div class="container" style="margin:auto;text-align: center;">

		<h1>
			Error <small> - Something was wrong!!</small>
		</h1>

<div class="row">
			<div  style="text-align: center;">
 <table id="tableError"  border="0.2" style="margin: 0 auto;">
        <tr>
            <td>Date</td>
            <td>${timestamp}</td>
        </tr>
        <tr>
            <td>Error</td>
            <td>${error}</td>
        </tr>
        <tr>
            <td>Status</td>
            <td>${status}</td>
        </tr>
        <tr>
            <td>Message</td>
            <td>${message}</td>
        </tr>
        <tr>
            <td>Cause</td>
            <td>${trace}</td>
        </tr>
       <!--   <tr>
            <td>Trace</td>
            <td>
                <pre>${trace}</pre>
            </td>
        </tr>-->
    </table>
</div>
</div>
<br></br>

<button type="button" title="Return to the previous page"  onclick="javascript:window.history.go(-1)">Return to the previous page</button>
  	</div>
  
  
<hr></hr>
</body>
</html>