<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/6
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Servlet-jCuckoo</title>
  <script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>
</head>
<body>
<form >
  用户名：<input type="text" id="userName"/><br/>
  密码：<input type="password" id="userPwd"/><br/>
  <input type="button" value="登录" id="sender">
</form>
<div id="messageDiv"></div>

<script>
    $('#sender').click(function(){
        var userName=document.getElementById('userName').value;
        var userPwd=document.getElementById('userPwd').value;
        var user={userName:userName,userPwd:userPwd};
        var url="LoginUserServlet";

        $.post(url, JSON.stringify(user), function(data) {
            console.log(data);
            $("#messageDiv").html(data);
            var json=JSON.parse(data);
            alert(json.message);
        });
        /* $.ajax({
           type:'post',
           url:url,
           dataType:"json",
           data:JSON.stringify(user),
           success: function (data) {
               var user=data.user;
                $("#messageDiv").html(JSON.stringify(user));
                alert(data.message);
           },
           error: function (data) {
                alert(data.message);
                $("#messageDiv").html("");
           }
       });  */

    });
</script>
</body>
</html>
