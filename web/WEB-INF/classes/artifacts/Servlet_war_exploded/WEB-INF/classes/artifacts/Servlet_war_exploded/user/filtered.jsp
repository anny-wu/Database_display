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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css" integrity="sha384-b6lVK+yci+bfDmaY1u0zE8YYJt0TZxLEAFyYSLHId4xoVvsrQu3INevFKo+Xir8e" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <title>Database Display</title>

    <style>
        body{
            text-align: center;
        }

        h2 {
            text-align: center;
            margin-top: 50px;
        }

        a {
            color: black;
            text-decoration: none;
            margin-right: 20px;
        }

        #controls{
            text-align:right;
        }
        .controls{
            border: none;
            cursor: pointer;
            padding: 0px;
        }

        .iconS{
            width: 30px;
            height: 30px;
            fill: #0c6dfd;
        }

        .display{
            margin-top:20px;
            margin-bottom: 20px;
        }

        table {
            width: 800px;
        }

    </style>
</head>

<body>
<div>
    <h2>Users</h2>
</div>
<div class="row">
    <div class="col-8 m-auto">
        <div id="controls">
            <form class="display" action="../Logout">
                <button class="btn btn-secondary" type="submit" value="Logout">Log Out</button>
            </form>
            <div>
                <form class="display d-flex justify-content-between" method="post">
                    <div>
                        <input type="hidden" name="table_name" value="${table_name}">
                        <input type="hidden" name="pageS" value="${pageS}">
                        <input type="hidden" name="type" value="user">
                        <c:if test="${count > 0}">
                            <button id="edit" type="submit" formaction="../editUser.jsp"
                                    class="btn btn-primary btn-sm">Edit</button>
                        </c:if>
                    </div>
                    <div>
                        Display


                        <select id="ps" name="pageSize">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                        </select>
                        rows per page
                        <button class="btn btn-outline-primary btn-sm"
                                formaction="QueryUserByPage"type="submit">Go</button>
                    </div>
                </form>
            </div>


            <table class="table table-striped table-hover mx-auto">
                <thead class="table-info">
                <tr>
                    <%
                        Class<?> cls = Class.forName("com.annyw.pojo.User");
                        Field[] fields = cls.getDeclaredFields();
                        for(Field f: fields){
                            String fname = f.getName().toUpperCase();
                    %>
                    <th scope="col"><%=fname%></th>
                    <%
                        }
                    %>
                </tr>
                </thead>
                <tbody>
                <%
                    Page p = (Page)request.getAttribute("page");
                    List<Object> users = p.getUsers();
                %>
                <%
                    for (int i = 0; i < users.size(); i++) {
                        User user = (User)users.get(i);
                %>
                <tr>
                    <%
                        for (Field field : fields) {
                            field.setAccessible(true);
                    %>
                    <td><%=field.get(user)%></td>
                    <% } %>
                </tr>
                <% } %>
                </tbody>
            </table>
            <%
                int currP = p.getCurrentPage();
                int total = p.getTotalCount();
            %>
            <nav class="m-auto">
                <ul class="pagination justify-content-center">
                    <%if (p.getCurrentPage() == 1) {%>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true"><span aria-hidden="true">&laquo;</span></a>
                    </li>
                    <%}else{%>
                    <li class="page-item">
                        <a class="page-link" href="QueryUserByPage?currentPage=<%=currP-1%>&pageSize=${pageS}"
                           aria-disabled="true"><span aria-hidden="true">&laquo;</span></a>
                    </li>
                    <%}%>


                    <%
                        for (int i = 1; i <= total; i++) {
                            if (p.getCurrentPage() == i) {
                    %>          <li class="page-item active" aria-current="page">
                        <%      }else{ %>
                    <li class="page-item">
                        <% } %>
                        <a class="page-link"
                           href="QueryUserByPage?currentPage=<%=i%>&pageSize=${pageS}"><%=i%></a></li>
                    <%}%>
                    <%if (p.getCurrentPage() == p.getTotalCount()) {%>
                    <li class="page-item">
                        <a class="page-link disabled" href="#">&raquo;</a>
                    </li>
                    <%}else{%>
                    <li class="page-item">
                        <a class="page-link" href="QueryUserByPage?currentPage=<%=currP+1%>&pageSize=${pageS}">
                            &raquo;</a>
                    </li>
                    <%}%>

                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
<script>
    $("#ps").find("option[value='${pageS}']").attr("selected",true);
</script>
</html>

