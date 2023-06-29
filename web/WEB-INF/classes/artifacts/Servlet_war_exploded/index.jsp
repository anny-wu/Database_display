<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
  <title></title>
</head>
<body>
<h1>Welcome to Database Users</h1>
<form action="QueryUserByPage">
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

