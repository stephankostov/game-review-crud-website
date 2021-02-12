<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Games List</title>
</head>
<body>
    <h1> Welcome to IGDB </h1>
    <h3><a href="Login">sign in</a></h3>
</body>
    <h3> Games </h3>
    <div>
        <table>
            <tr>
                <th>Game</th>
                <th>Rating</th>
            </tr>
            <c:forEach var="rating" items="${gameRatings}" >
                <tr>
                    <td><a href="Game/${rating.key.name}">${rating.key.name}</a></td>
                    <td>${rating.value}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</html>