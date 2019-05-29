<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Crud_Spring</title>
</head>
<body>
	<h3>Manejar Empleados</h3>
        

        
	<form:form method="post" action="/Prueba_Spring/employee.html" commandName="employee">
	    <div>
		<table style="width: 300px">
                        <tr>
				<td>Id *</td>
				<td><form:input type="text" path="id" readonly="true" /></td>
			</tr>
			<tr>
				<td>Nombre *</td>
				<td><form:input type="text" path="name" /></td>
			</tr>
			<tr>
				<td>Salario *</td>
				<td><form:input type="text" path="salary" /></td>
			</tr>
			<tr>
				<td>Designacion *</td>
				<td><form:input type="text" path="designation" /></td>
			</tr>
		
			<tr>
				<td></td>
				<td><input type="submit" value="Enviar" /></td>
			</tr>
		</table>
	    </div>
	</form:form>
	<br>
	<br>
	<h3>Lista de Empleados</h3>
        <table border="1">
		<tr>                    
			<th>Nombre</th>
			<th>Salario</th>
			<th>Desgnacion</th>		
			<th>Accion</th>
		</tr>
                
		<c:forEach items="${employeeList}" var="emp">
			<tr>
				<td width="60" align="center">${emp.name}</td>			
				<td width="60" align="center">${emp.salary}</td>
				<td width="60" align="center">${emp.designation}</td>				
				<td width="60" align="center"><a href="edit/${emp.id}">Actualizar</a>/<a href="delete/${emp.id}">Eliminar</a></td>                                
			</tr>
		</c:forEach>
	</table>
</body>
</html>