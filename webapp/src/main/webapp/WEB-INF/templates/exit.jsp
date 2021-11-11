<%@ page import="java.util.List" %>
<%@ page import="ru.lanit.repository.dto.Address" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.springframework.ui.Model" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" %>

<html>
<body>

<style type="text/css">
	TABLE {
		width: 400px;
		border-collapse: collapse;
	}
	TD, TH {
		padding: 2px;
		border: 2px solid black;
	}
	TH {
		background: #b4eea0;
	}
	TD {
		background: bisque;
	}
</style>

<p>Фамилия: ${person.surname}</p>
<p>Имя:	${person.name} </p>
<p>Отчество: ${person.patronymic}</p>

<table>
	<tr>
		<th>id</th>
		<th>City</th>
		<th>Street</th>
		<th>Persons</th>
	</tr>

	<c:forEach var="address" items="${listOfAddresses}" >
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