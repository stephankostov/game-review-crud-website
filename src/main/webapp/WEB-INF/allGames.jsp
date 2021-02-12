<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Game Info</title>
</head>
<body>
<h3> Games </h3>
<div>
    <table>
        <tr>
            <th>Game</th>
            <th>Rating</th>
        </tr>
        <c:forEach var="rating" items="${gameRatings}" >
            <tr>
                <td><a href="Game/${rating.key.gameId}">${rating.key.name}</a></td>
                <td>${rating.value}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>