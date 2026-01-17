<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Login Page</title>
</head>
<body>
<h1 align="center">Employee Login</h1>
<form action="Login" method="post">
<div align="center">
    <label>UserName: <input type="text" id="Username"
                            name="Username"></label>
</div>
<br>
<div align="center">
    <label>Password: <input type="text" id="password"
                            name="password"></label>
</div>
<br>
<div align="center">
    <input type="submit" value="Login">
</div>
</form>

</body>
</html>