<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css" integrity="sha384-b6lVK+yci+bfDmaY1u0zE8YYJt0TZxLEAFyYSLHId4xoVvsrQu3INevFKo+Xir8e" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
</head>
<style>
    .error{
        color:red;
    }

    .flabel{
        margin-top: 20px;
    }
    #showpassword{
        float:right;
        margin-left: 10px;
        margin-top: -25px;
        position: relative;
        z-index: 2;
    }
</style>
<body>
<div class="cover-container d-flex h-100 p-3 flex-column align-items-center justify-content-center">
    <h1>Welcome to Database Users</h1>
        <form>
            <div class="row">
                <button class="flabel btn btn-info" type="submit" formaction="QueryByUserPage">Login as admin</button>
            </div>
            <div class="row">
                <button class="flabel btn btn-info" type="submit" formaction="QueryByUserPager">Login as user</button>
            </div>
        </form>


</div>
</body>
</html>

