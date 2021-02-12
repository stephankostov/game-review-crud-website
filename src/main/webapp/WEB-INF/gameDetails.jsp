<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Game Info</title>
</head>
<body>

</body>
<h3> ${game.name} </h3>
<div>
    <table>
        <tr>
            <th>Developer</th>
            <th>Engine</th>
            <th>Release Date</th>
        </tr>
        <tr>
            <td>${game.developer.name}</td>
            <td>game engine...</td>
            <td>release data...</td>
        </tr>
    </table>
    <a href="ReviewGame/${game.gameId}">Add Review</a>
</div>
</html>