<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Games List</title>
</head>
<body>

    <p>Welcome to GameReview ${user.username}</p>

    <h1> Games List: </h1>
    <div>
        <table>
            <tr>
                <th>GameName</th>
                <th>Score</th>
            </tr>
            <c:forEach items="${allGames}" var="game">
                <tr>
                    <td>${game.name}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>