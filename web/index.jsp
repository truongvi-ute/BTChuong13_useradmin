<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>User Form</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
    <c:choose>
        <c:when test="${user != null}">
            <h1>Update User</h1>
            <p>Update the user information below.</p>
            
            <form action="emailList" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="userId" value="${user.userId}">
                
                <label>Email:</label>
                <input type="email" name="email" value="${user.email}" required><br>
                
                <label>First Name:</label>
                <input type="text" name="firstName" value="${user.firstName}" required><br>
                
                <label>Last Name:</label>
                <input type="text" name="lastName" value="${user.lastName}" required><br>
                
                <label>&nbsp;</label>
                <input type="submit" value="Update User" id="submit">
            </form>
        </c:when>
        <c:otherwise>
            <h1>Add User</h1>
            <p>Enter user information below.</p>
            
            <form action="emailList" method="post">
                <input type="hidden" name="action" value="add">
                
                <label>Email:</label>
                <input type="email" name="email" required><br>
                
                <label>First Name:</label>
                <input type="text" name="firstName" required><br>
                
                <label>Last Name:</label>
                <input type="text" name="lastName" required><br>
                
                <label>&nbsp;</label>
                <input type="submit" value="Add User" id="submit">
            </form>
        </c:otherwise>
    </c:choose>
    
    <br>
    <a href="emailList?action=list">View All Users</a>
</body>
</html>