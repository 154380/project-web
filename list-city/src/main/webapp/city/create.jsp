<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Thêm thành phố</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/create.css">
</head>
<body>
<div align="center" class="khung">
    <div>
        <h2>Thêm thành phố</h2>
    </div>
    <div>
        <form method="post">
            <table border="1" cellpadding="5" class="cot">
                <tr>
                    <th>Tên:</th>
                    <td>
                        <input type="text" name="name" id="name" size="65" required/>
                    </td>
                </tr>
                <tr>
                    <th>Quốc gia:</th>
                    <td>
                        <select name="country" id="country" >
                            <c:forEach var="country" items="${listCountry}">
                                <option name="country" ><c:out value="${country.name}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Diện tích:</th>
                    <td>
                        <input type="number" name="area" id="area" size="65" required/>
                    </td>
                </tr>
                <tr>
                    <th>Dân số:</th>
                    <td>
                        <input type="number" name="population" id="population" size="65" required/>
                    </td>
                </tr>
                <tr>
                    <th>GDP:</th>
                    <td>
                        <input type="number" name="GDP" id="GDP" size="65" required />
                    </td>
                </tr>
                <tr>
                    <th>Giới thiệu:</th>
                    <td>
                        <input type="text" name="description" id="description" size="65" required/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input class="btn btn-warning" type="submit" value="Nhập thành phố"/>
                        <a class="btn btn-warning" href="/city">Thoát</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>