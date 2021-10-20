<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@page contentType="text/html; charset=UTF-8" %>

<html>
<body>
<% String name = (String) session.getAttribute("name"); 
	String surname = (String) session.getAttribute("surname");
	String patronymic = (String) session.getAttribute("patronymic");%>
<p>Фамилия:	<%= surname %> </p>
<p>Имя:	<%= name %> </p>
<p>Отчество: <%= patronymic %> </p>

<p>Все люди из списка:</p>

<sql:setDataSource
	  var="postgres"
	  driver="org.postgresql.Driver"
	  url="jdbc:postgresql://localhost:5432/person_db"
	  user="timur" password=""
	  />

<sql:query dataSource="${postgres}" var="listOfPersons">
	SELECT * FROM person;
</sql:query>

<table>
	<tr>
		<th>ID</th>
		<th>Surname</th>
		<th>Name</th>
		<th>Patronymic</th>
	</tr>
	<c:forEach var="person" items="${listOfPersons.rows}">
		<tr>
			<td><c:out value="${person.id}" /></td>
			<td><c:out value="${person.surname}" /></td>
			<td><c:out value="${person.name}" /></td>
			<td><c:out value="${person.patronymic}" /></td>
		</tr>
	</c:forEach>
</table>

</body>
</html>