<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
</head>
<body>
<% session = request.getSession();
String nom = (String) session.getAttribute("nom");
	if (nom == null ){ %>
		<jsp:forward page="index.jsp" />
<%	}
	else {
%>

	<ul>
		<li><a href="ajout.jsp">Ajouter</a></li>
		<li><a href="chercher.jsp">Chercher</a></li>
	</ul>
	<%
	} %>
</body>
</html>