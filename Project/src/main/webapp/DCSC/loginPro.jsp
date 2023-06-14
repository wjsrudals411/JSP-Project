

<%@page import="Login.LoginDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
String stNum = request.getParameter("stNum");
String pwd = request.getParameter("pwd");

LoginDAO loginDAO = new LoginDAO();
boolean loginResult = loginDAO.checkLogin(stNum, pwd);

System.out.println("학번: " + stNum);
System.out.println("비밀번호: " + pwd);

if (loginResult) {
	response.sendRedirect("main.jsp");
} else {
    response.sendRedirect("index.jsp");
}


 %>