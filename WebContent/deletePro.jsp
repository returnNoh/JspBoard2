<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="NIW.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>삭제 확인</title>
<%
String passwd = request.getParameter("passwd");
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");
BoardDAO dao = new BoardDAO();

boolean check = dao.boardDelete(passwd, num);

if(check==true){
%>
<script>
alert("삭제성공");
location.href="list.jsp?pageNum=<%=pageNum%>";
</script>
<%}else{ %>
<script>
alert("삭제실패");
history.back();
</script>
<%} %>
</head>
<body>

</body>
</html>