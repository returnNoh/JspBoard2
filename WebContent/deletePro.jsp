<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="NIW.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>삭제 확인</title>

<c:if test="${check==true}">

<script>
alert("삭제성공");
<%-- location.href="list.jsp?pageNum=<%=pageNum%>"; --%>
</script>
<meta http-equiv="Refresh" content="0; url=/JspBoard2/list.do?pageNum=${pageNum}">

</c:if>

<c:if test="${check==false}">

<script>
alert("삭제실패");
history.back();
</script>
</c:if>



</head>
<body>

</body>
</html>