<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 15/07/2021
  Time: 11:14 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Xóa thành phố</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/create.css">
</head>
<body>
<div align="center" class="khung">
    <div>
        <h2>Xóa thành phố</h2>
    </div>
    <br>
    <div>
        <h5>Bạn có chắc chắn muốn xóa thành phố </h5>
    </div>
    <br>
    <div>
        <form method="post">
            <input class="btn btn-warning" type="submit" value="Xóa"/>
            <a class="btn btn-warning" href="/city">Thoát</a>
        </form>
    </div>
</div>
</body>
</html>
