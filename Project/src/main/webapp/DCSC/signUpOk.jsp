<%--
작성자 : jeon
작성일 : 2023. 6. 13.

--%>

<%@page import="Login.LoginDAO"%>
<%@page import="Login.LoginDTO"%>
<%


%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<% request.setCharacterEncoding("UTF-8");
   
     String name = request.getParameter("name"); 
     String stNum = request.getParameter("stNum");
     String pwd = request.getParameter("pwd");
     String email = request.getParameter("email"); 
     String grade = request.getParameter("grade"); 
     String position = request.getParameter("position"); 
	
	
  
  	LoginDTO dto = new LoginDTO(name, stNum, pwd, email, grade, position);
	LoginDAO dao = new LoginDAO();
  	
	dao.insertLogin(dto);
    
    // signUpOk.jsp로 이동
    response.sendRedirect("signUpOk.jsp");
	%>
  
</body>
</html>