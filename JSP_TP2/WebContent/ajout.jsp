<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajouter</title>
</head>
<body>

<% session = request.getSession();
String nom = (String) session.getAttribute("nom");
	if (nom != null ){
%>
<form name="Porduit" id="Produit" action="Produit" method="POST">
	<div>ID Produit : </div>
	<input id="idp" name="idp" type="number" value="" required />
	<div>Nom Produit : </div>
	<input id="nomp" name="nomp" type="text" value="" required />
	<div>Quantité : </div>
	<input id="qte" name="qte" type="number" value="" required /> </br>
	<input id="submit" name="submit" type="submit" value="Submit"/>
	<input id="reset" name="reset" type="reset" value="Reinitialiser"/>  
</form>
<% } else { %> 
	<jsp:forward page="index.jsp"></jsp:forward>
<% } %>
</body>
</html>