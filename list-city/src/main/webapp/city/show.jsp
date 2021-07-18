<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 15/07/2021
  Time: 1:04 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Thành phố</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/create.css">
</head>
<body>
<div class="khung">
    <div>
        <form method="post">
            <div>
                <h2 align="center" >Thành phố <c:out value='${city.name}' /></h2>
                <div class="suatp">
                    <a class="btn btn-warning" href="/city">Xem danh sách thành phố</a>
                </div>
            </div>
            <c:if test="${city != null}">
                <input type="hidden" name="id" value="<c:out value='${city.id}' />"/>
            </c:if>
            <br><br>
            <h5 >Tên: <c:out value='${city.name}' /> </h5>
            <h5>Quốc gia: <c:out value='${city.country}' /> </h5>
            <h5>Diện tích: <c:out value='${city.area}' /> </h5>
            <h5 >Dân số: <c:out value='${city.population}' /> </h5>
            <h5 >GDP: <c:out value='${city.GDP}' /> </h5>
            <h5 >Giới thiệu: <c:out value='${city.description}' /> </h5>
            <br><br>
            <div class="suatp">
                <a class="btn btn-warning" href="/city?action=edit&id=${city.id}">Chỉnh sửa</a>
                <a class="btn btn-warning" href="/city?action=delete&id=${city.id}">Xóa</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>