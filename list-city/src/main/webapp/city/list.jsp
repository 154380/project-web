<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 13/07/2021
  Time: 4:35 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Danh sách thành phố</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/list.css">
</head>
<body>
<div class="khung">
    <div class="tieude">
        <h2>Danh sách thành phố </h2>
    </div>
    <br>
    <div style="padding-left: 470px;">
        <a class="btn btn-warning" href="/city?action=create" id="themtp">Thêm thành phố</a>
    </div>
    <div>
        <form method="post">
            <input type="text" name="txtSearch" id="txtSearch" size="15" value="<c:out value='${txtSearch}' />">
            <a class="btn btn-warning" href="/city?action=search">Search</a>
        </form>
    </div>
    <br>
    <div>
        <table border="3" cellpadding="18">
            <tr class="cot1">
                <th>#</th>
                <th>Thành phố</th>
                <th>Quốc gia</th>
                <th></th>
            </tr>
            <c:forEach var="city" items="${listCity}">
                <tr class="cot" >
                    <td><c:out value="${city.id}"/></td>
                    <td><a href="/city?action=show&id=${city.id}"><c:out value="${city.name}"/></a></td>
                    <td><c:out value="${city.country}"/></td>
                    <td align="center">
                        <a class="btn btn-warning" href="/city?action=edit&id=${city.id}">Chỉnh sửa</a>
                        <a class="btn btn-warning" href="/city?action=delete&id=${city.id}">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="">
        <c:forEach begin="1" end="${countPage}" var="i">
            <a class="btn btn-warning" href="#">${i}</a>
        </c:forEach>
    </div>
</div>
</body>
</html>