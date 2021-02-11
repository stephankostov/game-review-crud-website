<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
</head>
<body>
    <h1>Login to Game Review</h1>
    <form action="LoginSubmit" method="post">
        <div>${message}</div>
        <div>
            <label for="username"> User name</label>
            <input type="text" name="username" required="required" />
        </div>
        <div>
            <label for="password"></label>
            <input type="password" name="password" required="required" />
        </div>
        <div>
            <button type="submit">Login</button>
        </div>
    </form>

</body>
</html>