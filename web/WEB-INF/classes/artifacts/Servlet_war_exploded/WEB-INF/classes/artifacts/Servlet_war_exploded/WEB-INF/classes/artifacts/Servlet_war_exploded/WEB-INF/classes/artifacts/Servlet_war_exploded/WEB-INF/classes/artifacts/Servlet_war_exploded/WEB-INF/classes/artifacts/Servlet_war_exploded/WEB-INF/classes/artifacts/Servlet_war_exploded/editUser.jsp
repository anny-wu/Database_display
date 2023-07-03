<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.annyw.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Field" %>
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
    <title>editUser</title>
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

    #labele{
        margin-top: 10px;
    }
    #deleteS{
        margin-top: 10px;
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
    <h2>Edit User</h2>
</div>
<div class="row">
    <div class="col-8 m-auto back">
                <form>
                    <input type="hidden" name="pageS" value="<%=request.getParameter("pageS")%>">
                    <input type="hidden" id="selected" name="selected" value="1">
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
                        <label id="labele" class="col-2 col-form-label"><strong>Edit</strong></label>
                        <div class="col-6">
                            <div class="form-outline">
                                <select id="selectS" class="form-select" aria-label="Default select example">
                                    <%
                                        for(User u : users){
                                            String str = "User " + u.getid() + " ( " + u.getusername() + " )";%>
                                    <option value="<%=u.getid()%>"><%=str%></option>
                                    <%}%>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">

                        <%
                            Class<?> cls = Class.forName("com.annyw.pojo.User");
                            Field[] fields = cls.getDeclaredFields();
                            for(int i = 1; i < fields.length-1; i++){
                                fields[i].setAccessible(true);
                                String fname = fields[i].getName().toUpperCase();
                        %>
                        <div class="col-6">
                            <label class="flabel col-2 col-form-label"><strong><%=fname%></strong></label>

                            <div class="form-outline w-50">
                                <input type="<%=fields[i].getName()%>" class="form-control edit" id="<%=fields[i].getName()%>" name="<%=fields[i].getName()%>"
                                       aria-describedby="<%=fields[i].getName()%>+'Help'"
                                       placeholder="<%=fields[i].getName()%>">
                            </div>
                        </div>


                        <%
                            }
                        %>
                    </div>
                    <div id="buttons">
                        <div>
                            <button id="edit" type="submit" class="btn btn-lg btn-primary controlB disabled"
                                    formaction="EditUser">Edit
                            </button>
                            <button type="submit" class="btn btn-lg btn-primary controlB" formaction="QueryUserByPage">Back</button>
                        </div>
                    </div>
                    </form>
    </div>
</div>
                        <%}
                }

            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (sqlSession != null) {
                    sqlSession.close();
                }
            }
                %>

</body>
<script>
    $(document).ready(function() {
        $('#selectS').change(function () {
            $("#selected").attr("value", $("#selectS").val());
        });
        $('.edit').on('input',function() {
            var empty = false;
            $('.edit').each(function() {
                if ($(this).val() == '') {
                    empty = true;
                }
            });

            if (empty) {
                $('#edit').addClass('disabled');
            }
            else{
                $('#edit').removeClass('disabled');
            }
         });
    });
</script>
</html>
