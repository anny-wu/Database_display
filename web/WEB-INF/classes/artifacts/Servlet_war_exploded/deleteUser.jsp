<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.annyw.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.annyw.util.MybatisUtils" %>
<%@ page import="com.annyw.dao.UserDao" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <title>Delete User</title>
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

    .form-select{
        margin-top: 20px;
    }

    #buttons{
        margin-top:20px;
        margin-bottom:20px;
        float:right;
    }
    .controlB{
        width: 200px;
        margin: 10px;
    }
</style>

<body>
<div>
    <h2>Delete User</h2>
</div>
<div class="row">
    <div class="col-8 m-auto back">
        <form>
            <input type="hidden" name="pageS" value="<%=request.getParameter("pageS")%>">
            <input type="hidden" id="deleted" name="deleted" value="1">
            <input type="hidden" name="action" value="delete">
            <div class="form-group row">
                <label class="flabel col-2 col-form-label"><strong>TABLE NAME</strong></label>
                <div class="col-4">
                    <input type="text" readonly class="flabel col-4 form-control-plaintext" name="table_name"
                           value="<%=request.getParameter("table_name")%>">
                </div>
            </div>
            <%
                SqlSession sqlSession = null;
                try {
                    sqlSession = MybatisUtils.getSqlSession();
                    UserDao mapper = sqlSession.getMapper(UserDao.class);
                    String table_name = request.getParameter("table_name");
                    List<User> users = mapper.getAllUsers(table_name);
                    if(users != null){
            %>
            <div class="form-group row">
                <label class="flabel col-2 col-form-label"><strong>DELETE</strong></label>
                <div class="col-4">
                    <div class="form-outline">
                        <select id="deleteS" class="form-select" aria-label="Default select example">
                            <%for(User u : users){
                                String str = "User " + u.getid() + " ( " + u.getusername() + " )";%>
                            <option value="<%=u.getid()%>"><%=str%></option>
                            <%}%>
                        </select>
                    </div>
                </div>
            </div>
            <%      }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                if (sqlSession != null) {
                    sqlSession.close();
                }
            }%>
            <div id="buttons">
                <div>
                    <button type="submit" class="btn btn-lg btn-primary controlB" formaction="user/Interaction">Delete
                    </button>
                    <button type="submit" class="btn btn-lg btn-primary controlB" formaction="user/EditUserByPage">Back
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script>
    //Record the selected user id for deletion
    $(document).ready(function() {
        $('#deleteS').change(function () {
            $("#deleted").attr("value", $("#deleteS").val());
        });
    });

</script>
</html>
