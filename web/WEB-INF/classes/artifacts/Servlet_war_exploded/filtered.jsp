<%@page import="com.annyw.pojo.Page" %>
<%@page import="com.annyw.pojo.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <title>Database Display</title>

    <style>
        tr, h2 {
            text-align: center;
            margin: 50px;
        }

        a {
            color: black;
            margin-right: 20px;
        }

        table {
            width: 800px;
        }

        body {
            text-align: center;
        }

        form {
            display: inline-block;
            margin-bottom: 100px;
        }
    </style>
</head>
<body>
<div>
    <h2>Users</h2>
</div>
<div row>
    <div class="col-8 m-auto">
        <table class="table table-striped table-hover mx-auto">
            <thead class="table-info">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Username</th>
                <th scope="col">Age</th>
                <th scope="col">Update_time</th>
            </tr>
            </thead>
            <tbody>
            </tr>
            <%
                Page p = (Page)request.getAttribute("page");
                List<Object> users = p.getUsers();
            %>
            <%
                for (int i = 0; i < users.size(); i++) {
                    User user = (User)users.get(i);
                    Class<?> cls = Class.forName("com.annyw.pojo.User");
                    Field[] fields = cls.getDeclaredFields();
            %>

            <tr>

                <%
                    for (Field field : fields) {
                        field.setAccessible(true);
                %>


                <td><%=field.get(user)%>
                </td>
                <%
                    }
                %>
            </tr>
            <%

                }
            %>
            </tbody>
        </table>
<br>

<%
    int currP = p.getCurrentPage();
    int pageS = p.getPageSize();
    int total = p.getTotalCount();
    if (p.getCurrentPage() == 1) {
%>
<nav>
    <ul class="pagination justify-content-center">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1" aria-disabled="true"><span aria-hidden="true">&laquo;</span></a>
        </li>
        <%
            for (int i = 1; i <= total; i++) {
                if (p.getCurrentPage() == i) {
        %>
        <li class="page-item active" aria-current="page">
                <%}
                    else{
                %>
        <li class="page-item">
            <%
                }
            %>
            <a class="page-link" href="QueryUserByPage?currentPage=<%=i%>&pageSize=<%=pageS%>"><%=i%>
            </a></li>
        <%
            }
        %>
        <li class="page-item">
            <a class="page-link" href="QueryUserByPage?currentPage=<%=currP+1%>&pageSize=<%=pageS%>">&raquo;</a>
        </li>
    </ul>
</nav>
<%}else if (p.getCurrentPage() == p.getTotalCount()) {%>
<nav>
    <ul class="pagination justify-content-center">
        <li class="page-item">
            <a class="page-link" href="QueryUserByPage?currentPage=<%=currP-1%>&pageSize=<%=pageS%>"><span
                    aria-hidden="true">&laquo;</span></a>
        </li>
        <%
            for (int i = 1; i <= total; i++) {
                if (p.getCurrentPage() == i) {
        %>
        <li class="page-item active" aria-current="page">
                <%}
                    else{
                %>
        <li class="page-item">
            <%
                }
            %>
            <a class="page-link" href="QueryUserByPage?currentPage=<%=i%>&pageSize=<%=pageS%>"><%=i%>
            </a></li>
        <%
            }
        %>
        <li class="page-item">
            <a class="page-link disabled" href="#">&raquo;
            </a>
        </li>
    </ul>
</nav>
<%
}
else {
%>
<nav>
    <ul class="pagination justify-content-center">
        <li class="page-item">
            <a class="page-link" href="QueryUserByPage?currentPage=<%=currP-1%>&pageSize=<%=pageS%>"
               aria-disabled="true"><span aria-hidden="true">&laquo;</span></a>
        </li>
        <%
            for (int i = 1; i <= total; i++) {
                if (p.getCurrentPage() == i) {
        %>
        <li class="page-item active" aria-current="page">
                <%}
                    else{
                %>
        <li class="page-item">
            <%
                }
            %>
            <a class="page-link" href="QueryUserByPage?currentPage=<%=i%>&pageSize=<%=pageS%>"><%=i%>
            </a></li>
        <%
            }
        %>
        <li class="page-item">
            <a class="page-link" href="QueryUserByPage?currentPage=<%=currP+1%>&pageSize=<%=pageS%>" tabindex="-1"
               aria-disabled="true"><span aria-hidden="true">&raquo;</span></a>
        </li>
    </ul>
</nav>
<%}%>
<br>
<form action="QueryUserByPage">
    Display
    <select name="pageSize">
        <option <%if (p.getPageSize() == 1) {%> selected <%}%>value="1">1</option>
        <option <%if (p.getPageSize() == 2) {%> selected <%}%>value="2">2</option>
        <option <%if (p.getPageSize() == 3) {%> selected <%}%>value="3">3</option>
        <option <%if (p.getPageSize() == 4) {%> selected <%}%>value="4">4</option>
    </select>
    rows per page
    <button class="btn btn-outline-primary btn-sm" type="submit">Go</button>
</form>
    </div>
</div>
</body>
</html>

