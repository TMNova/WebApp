<%@ page import="java.util.List" %>
<%@ page import="ru.lanit.entity.AddressEntity" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" %>

<html>
<body>
<% String name = (String) session.getAttribute("name"); 
	String surname = (String) session.getAttribute("surname");
	String patronymic = (String) session.getAttribute("patronymic");
	List<AddressEntity> addresses = (List<AddressEntity>) session.getAttribute("addresses");
	session.setAttribute("listAddresses", addresses);
%>
<p>Фамилия:	<%= surname %> </p>
<p>Имя:	<%= name %> </p>
<p>Отчество: <%= patronymic %> </p>

<table>
	<tr>
		<th>id</th>
		<th>City</th>
		<th>Street</th>
		<th>Persons</th>
	</tr>

	<c:forEach var="address" items="${listAddresses}" varStatus="count">
		<tr>
			<td><c:out value="${address.id}" /></td>
			<td><c:out value="${address.city}" /></td>
			<td><c:out value="${address.street}" /></td>
			<td><c:forEach var="person" items="${address.personList}" >
				<c:out value="${person.surname}" /><br>
				<c:out value="${person.name}" /><br>
				<c:out value="${person.patronymic}" />
				<p></p>
			</c:forEach></td>
			<p></p>
		</tr>
	</c:forEach>
</table>

</body>
</html>