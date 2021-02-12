<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Game Review</title>
</head>
<body>
    <h3> Review </h3>
    <sf:form method="post" action="${pageContext.request.contextPath}/ReviewSubmit" modelAttribute="review">

        <sf:hidden path="game" value="${game}" />
        <div>
            <sf:errors path="user" />
            <sf:label path="user">User</sf:label>
            <sf:select path="user" items="${allUsers}" required="required"/>
        </div>
        <div>
            <sf:errors path="rating" />
            <sf:label path="rating">Rating</sf:label>
            <sf:select path="rating" items="${ratingOptions}" required="required"/>
        </div>
        <div>
            <sf:errors path="review" />
            <sf:label path="review">Review</sf:label>
            <sf:input path="review" type="text" required="required" />
        </div>
        <div>
            <input type="submit" value="review game">
        </div>

    </sf:form>
</body>
</html>