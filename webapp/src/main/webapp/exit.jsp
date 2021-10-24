<%@ page import="ru.lanit.Person" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" %>

<html>
<body>
<% String name = (String) session.getAttribute("name"); 
	String surname = (String) session.getAttribute("surname");
	String patronymic = (String) session.getAttribute("patronymic");
	List<Person> listOfPersons = (List<Person>) session.getAttribute("persons");
	session.setAttribute("listPersons", listOfPersons);
%>
<p>Фамилия:	<%= surname %> </p>
<p>Имя:	<%= name %> </p>
<p>Отчество: <%= patronymic %> </p>

<table>
	<tr>
		<th>Surname</th>
		<th>Name</th>
		<th>Patronymic</th>
	</tr>

	<c:forEach var="person" items="${listPersons}">
		<tr>
			<td><c:out value="${person.surname}" /></td>
			<td><c:out value="${person.name}" /></td>
			<td><c:out value="${person.patronymic}" /></td>
		</tr>
	</c:forEach>
</table>

</body>
</html>