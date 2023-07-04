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
</body>
<script>
    $("#ps").find("option[value='${pageS}']").attr("selected",true);
</script>
</html>

