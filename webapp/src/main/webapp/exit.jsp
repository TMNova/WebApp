<%@page contentType="text/html; charset=UTF-8" %>

<html>
<body>
<% String name = (String) session.getAttribute("name"); 
	String surname = (String) session.getAttribute("surname");
	String patronymic = (String) session.getAttribute("patronymic");%>
<p>Фамилия:	<%= surname %> </p>
<p>Имя:	<%= name %> </p>
<p>Отчество: <%= patronymic %> </p>

</body>
</html>