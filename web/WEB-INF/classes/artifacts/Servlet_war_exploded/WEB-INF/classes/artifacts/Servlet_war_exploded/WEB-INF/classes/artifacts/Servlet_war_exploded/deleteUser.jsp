<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.annyw.pojo.Page" %>
<%@page import="com.annyw.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <title>addUser</title>
</head>
<style>
    h2{
        text-align: center;
    }
    .flabel{
        margin-top: 20px;
    }
    #add{
        margin-top: 20px;
    }
</style>

<body>
<div>
    <h2>Delete User</h2>
</div>
<div class="row">
    <div class="col-8 m-auto">
        <form action="AddUser">
            <div class="form-group row">
                <%
                    Class<?> cls = Class.forName("com.annyw.pojo.User");
                    Field[] fields = cls.getDeclaredFields();
                %>
                <label class="flabel col-sm-2 col-form-label">TABLE NAME</label>
                <div class="col-sm-10">
                    <input type="text" readonly class="flabel form-control-plaintext" name="table_name" value="<%=request.getParameter("table_name")%>">
                </div>
            </div>
            <div class="form-group row">
                <label class="flabel col-sm-2 col-form-label">ID</label>
                    <div class="col-sm-10">
                        <%
                            String pCount = request.getParameter("count");
                            if (pCount == null){
                                pCount = "0";
                            }
                            int nextId = Integer.parseInt(pCount)+1;
                        %>
                        <input type="text" readonly class="flabel form-control-plaintext" id=id" name="id"
                               value="<%=nextId%>">
                    </div>
            </div>
            <div class="form-group">
               <%
                   for(int i = 1; i < fields.length-1; i++){
                       String fname = fields[i].getName().toUpperCase();
               %>
                    <label class="flabel"><%=fname%></label>
                    <input type="<%=fields[i].getName()%>" class="form-control" name="<%=fields[i].getName()%>"
                           aria-describedby="<%=fields[i].getName()%>+'Help'"
                       placeholder="<%=fields[i].getName()%>">
                <%
                    }
                %>
            </div>
            <button type="submit" class="btn btn-primary" id="add">Add</button>
        </form>
        <form action="filtered.jsp">
            <button type="submit" class="btn btn-primary" id="back">Back</button>
        </form>
    </div>
</div>

</body>
</html>
