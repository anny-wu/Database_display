<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>
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

    .form-control{
        width:500px;
    }

    #showpassword{
        float:right;
        margin-top: -25px;
        right:10px;
        position: relative;
        z-index: 2;
    }

</style>
<body>
<div class="cover-container d-flex h-100 flex-column align-items-center justify-content-center">
    <h1>Register</h1>
    <form>
            <div>
                <label class="flabel"><strong>Email</strong></label>
                <input type="email" class="form-control" name="email"
                       aria-describedby="emailHelp"
                       placeholder="email" id="email">
                <span class="error" id="emailerror"></span>
            </div>
            <div>
            <label class="flabel"><strong>Username</strong></label>
            <input type="text" class="form-control" name="username"
                   aria-describedby="usernameHelp'"
                   placeholder="username" id="username">
            <span class="error" id="unameerror">${uerror}</span>
        </div>
        <div>
            <label class="flabel"><strong>Password</strong></label>
            <input type="password" class="form-control" name="password"
                   aria-describedby="passwordHelp"
                   placeholder="password" id="password">
            <i id="showpassword" class="bi bi-eye-slash"></i>
            <span class="error" id="pwderror">${perror}</span>
        </div>


        <div class="row">
            <button class="flabel btn btn-info" type="submit" onclick="return validateForm()">Sign up</button>
        </div>
    </form>
</div>
</body>
<script>
    $("#showpassword").on("click", function(){
        if($("#showpassword").hasClass("bi-eye")){
            $("#showpassword").removeClass("bi-eye");
            $("#showpassword").addClass("bi-eye-slash");
            $("input[name='password']").attr("type","password");
        }else{
            $("#showpassword").removeClass("bi-eye-slash");
            $("#showpassword").addClass("bi-eye");
            $("input[name='password']").attr("type","text");
        }
    });

    function validateForm(){
        return (validateEmail() && validateUsername());
    }

    function validateEmail(){

        var mailformat =
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        ;
        if($("#email").val().toLowerCase().match(mailformat))
        {
            $("#email").focus();
            return true;
        }
        else
        {
            $("#emailerror").text("Invalid email");
            $("#email").focus();
            return false;
        }
    }

    function validateUsername(){
         if($("#username").val() != null && $("#username").val() != ""){
            if(isAsciiPrintable($("#username").val())) {
                $("#username").focus();
                return true;
            }
             $("#unameerror").text("Invalid username");
             $("#email").focus();
            return false;
        }else{
             $("#unameerror").text("Username can't be blank");
            return false;
        }
    }
</script>
</html>

