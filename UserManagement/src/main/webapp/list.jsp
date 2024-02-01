<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 01/02/2024
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
    <h1 class="my-3">Quản Lý Người Dùng</h1>
    <div class="row d-flex mx-3">
        <div class="col-9">
            <a class="btn btn-warning" role="button" href="/user?action=create">Thêm mới</a>
        </div>
        <div class="col-3">
            <form class="d-flex" role="search" action="/user" method="post">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="country">
                <a class="btn btn-outline-secondary" type="submit" value=""></a>
            </form>
        </div>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Stt</th>
            <th scope="col">Tên Người Dùng</th>
            <th scope="col">Email</th>
            <th scope="col">Địa chỉ</th>


            <th scope="col">Chỉnh sửa</th>
            <th scope="col">Xoá</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="u" items="${user}" >
            <tr>
                <td>${u.id}</td>
                <td>${u.name}</td>
                <td>${u.email}</td>
                <td>${u.country}</td>

                <td>
                    <a class="btn btn-warning" href="/user?action=edit&id=${u.id}" role="button">Chỉnh
                        sửa</a>
                </td>
                <td>
                    <a class="btn btn-warning" href="/user?action=delete&id=${u.id}" role="button">Xoá</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
