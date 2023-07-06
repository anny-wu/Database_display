<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.lang.reflect.Field"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
        margin-top: 50px;
    }
    .back{
        background-color: lightsalmon;
        border-radius: 20px;
    }
    .flabel{
        margin-top: 20px;
    }
    .form-group{
        margin-top: 20px;
        margin-left: 20px;
    }
    .controlB{
        width: 200px;
        margin: 10px;
    }
    #buttons{
        margin-top:20px;
        margin-bottom:20px;
        float:right;
    }
</style>

<body>
<div>
    <h2>Add User</h2>
</div>
<div class="row">
    <div class="col-8 m-auto back">
        <form>
            <input type="hidden" name="pageS" value="<%=request.getParameter("pageS")%>">
            <input type="hidden" name="action" value="add">
            <div class="form-group row">
            <%
                Class<?> cls = Class.forName("com.annyw.pojo.User");
                Field[] fields = cls.getDeclaredFields();
            %>
                <label class="flabel col-2 col-form-label"><strong>TABLE NAME</strong></label>
                <div class="col-4">
                    <input type="text" readonly class="flabel col-2 form-control-plaintext" name="table_name"
                           value="<%=request.getParameter("table_name")%>">
                </div>

                <label class="flabel col-2 col-form-label"><strong>ID</strong></label>
                <div class="col-4">
                <%
                    String pCount = request.getParameter("count");
                    if (pCount == null){
                        pCount = "0";
                    }
                    int nextId = Integer.parseInt(pCount)+1;
                %>
                    <input type="text" readonly class="flabel col-2 form-control-plaintext" id=id" name="id"
                       value="<%=nextId%>">
                </div>
            </div>
            <div class="form-group row">
           <%
               for(int i = 1; i < fields.length-1; i++){
                   String fname = fields[i].getName().toUpperCase();
           %>
                <div class="col-6">
                    <label class="flabel col-2 col-form-label"><strong><%=fname%></strong></label>
                    <div class="form-outline w-50">
                        <input type="<%=fields[i].getName()%>" class="form-control edit" name="<%=fields[i].getName()%>"
                               aria-describedby="<%=fields[i].getName()%>+'Help'"
                               placeholder="<%=fields[i].getName()%>">
                    </div>
                </div>
           <%   }%>
            </div>
            <div id="buttons">
                <div>
                    <button id="add" type="submit" class="btn btn-lg btn-primary controlB disabled"
                    formaction="user/Interaction">Add</button>
                    <button type="submit" class="btn btn-lg btn-primary controlB" formaction="user/EditUserByPage">Back
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script>
    $(document).ready(function() {
        //Change add enable status when input is empty
        $('.edit').on('input', function () {
            var empty = false;
            $('.edit').each(function () {
                if ($(this).val() == '') {
                    empty = true;
                }
            });

            if (empty) {
                $('#add').addClass('disabled');
            } else {
                $('#add').removeClass('disabled');
            }
        });
    });
</script>
</html>
