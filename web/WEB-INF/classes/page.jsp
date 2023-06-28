<%@page import="com.annyw.pojo.Page"%>
<%@page import="com.annyw.pojo.User"%>

<%@page import="java.util.List"%>
<%@ page import="java.lang.reflect.Field" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Database Display</title>

  <style>
    td{
      width:100px;
    }
    th{
      width:100px;
    }

  </style>

</head>
<body>

<h2>Users</h2>
<table>
  <tr>
    <th>ID</th>
    <th>Username</th>
    <th>Age</th>
    <th>Update_time</th>
  </tr>
  <%
    Page p =  (Page)request.getAttribute("page");
    List<Object> users = p.getUsers();
    for (int i = 0; i < users.size(); i++){
      User user = (User) users.get(i);
      Class<?> cls = Class.forName("User");
      Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
  %>
  <tr>
    <td><%=field.get(user)%></td>
  </tr>
  <%
      }
    }
  %>
</table>
<br>

<%
  if(p.getCurrentPage() == p.getTotalCount()){
%>
<a href="QueryStudentByPage?currentPage=1&pageSize=<%=p.getPageSize()%>">Go to Top</a>
<a href="QueryStudentByPage?currentPage=<%=p.getCurrentPage()-1%>&pageSize=<%=p.getPageSize()%>">Previous</a>
<%
}else if(p.getCurrentPage() == 1){
%>
<a href="QueryStudentByPage?currentPage=<%=p.getCurrentPage()+1%>&pageSize=<%=p.getPageSize()%>">Next</a>
<a href="QueryStudentByPage?currentPage=<%=p.getTotalCount()%>&pageSize=<%=p.getPageSize()%>">Go to bottom</a>
<%
}else{
%>
<a href="QueryUserByPage?currentPage=1">Top</a>
<a href="QueryUserByPage?currentPage=<%=p.getCurrentPage()-1%>&pageSize=<%=p.getPageSize()%>">Previous</a>
<a href="QueryStudentByPage?currentPage=<%=p.getCurrentPage()+1%>&pageSize=<%=p.getPageSize()%>">Next</a>
<a href="QueryStudentByPage?currentPage=<%=p.getTotalCount()%>&pageSize=<%=p.getPageSize()%>">Bottom</a>
<%
  }
%>

<br>
<form action="QueryStudentByPage">
  Display
  <select name="pageSize">
    <option value="3">3</option>
    <option value="5">5</option>
    <option value="7">7</option>
    <option value="10">10</option>
  </select>
  rows
  <input type="submit" value="Go">
</form>
</body>
</html>

