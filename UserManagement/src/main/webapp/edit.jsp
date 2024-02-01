<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 01/02/2024
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm mới người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">

    <div class="row justify-content-center mt-4">
        <form class="col-md-6" action="/user?action=edit" method="post">
            <h1 class="">Chỉnh sửa thông tin</h1>
            <div class="form-group">
                <label for="txtId">ID</label>
                <input type="text" class="form-control" name="id" id="txtId" value="${requestScope["user"].getId()}">
            </div>
            <div class="form-group">
                <label for="name">Tên người dùng</label>
                <input type="text" class="form-control" name="name" id="name" value="${requestScope["user"].getName()}}">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" name="email" id="email" value="${requestScope["user"].getEmail()}">
            </div>
            <div class="form-group">
                <label for="country">Địa chỉ</label>
                <input type="text" class="form-control" name="country" id="country" value="${requestScope["user"].getCountry()}">
            </div>
            <%--            <div class="form-group">--%>
            <%--                <label for="producer">Nhà sản xuất</label>--%>
            <%--                <input type="text" class="form-control" name="producer" id="producer">--%>
            <%--            </div>--%>
            <div class="form-group mt-2">
                <button class="btn btn-success" role="button" type="submit">Chỉnh sửa</button>
                <button class="btn btn-warning" role="button">Quay lại</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
