<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP_TP2</title>
</head>
<body>

<% session = request.getSession();
String nom = (String) session.getAttribute("nom");
	if (nom == null ){
%>
<form id="loginform" name="loginform" method="GET" action="Connexion">
	<div>Login : </div>
	<input id="nom" name="nom" type="text" value=""/>
	<div>Mot de Passe : </div>
	<input id="mdp" name="mdp" type="password" value=""/> </br>
	<input id="submit" name="submit" type="submit" value="Submit"/>
	<input id="reset" name="reset" type="reset" value="Reinitialiser"/>  
</form>
<% }
	else{
%>
<jsp:forward page="menu.jsp" />
	<%
	}%>
</body>
</html>