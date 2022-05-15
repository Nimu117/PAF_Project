<%@ page import="model.Payment" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Payment Section </h1>
<form id="formItem" name="formItem">
 Card Number : 
 <input id="cNumber" name="cNumber" type="text" 
 class="form-control form-control-sm">
 <br> CVV: 
 <input id="CVV" name="CVV" type="text" 
 class="form-control form-control-sm">
 <br> Expire Date: 
 <input id="exDate" name="exDate" type="text" 
 class="form-control form-control-sm">
 <br> Amount: 
 <input id="amount" name="amount" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Payment paymentObj = new Payment(); 
 	 out.print(paymentObj.readPayment());
 %>
</div>
</div> </div> </div> 
</body>
</html>
