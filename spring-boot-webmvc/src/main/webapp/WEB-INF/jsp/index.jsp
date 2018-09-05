<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Insert title here</title>
        <link  rel="stylesheet" href="${pageContext.request.contextPath}/style.css"></link >
    </head>
    <body>
        <h1>Welcome</h1>

        <form:form method="POST" action="${pageContext.request.contextPath}/addEmployee">
            <label>Enter Employee name</label>
            <input type="text" value="John" name="empName"> <input
                type="submit" />
        </form:form>
        <br> Employee Name: ${employeeName}
        <c:out value="${employeeName}"/>
    </body>
</html>