<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="NIW.*,java.util.*,java.text.SimpleDateFormat"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#e0ffff">
<center><b>글목록(전체 글:${count})</b>
<table width="700">
<tr>
    <td align="right" bgcolor="#b0e0e6">
    <a href="/JspBoard2/writeForm.do">글쓰기</a>
    </td>
</table>

<c:if test="${count==0}">
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
<tr>
<td align="center">게시판에 저장된 글이 없습니다.</td>
</tr>
</table>
</c:if>


<c:if test="${count>0}">
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30" bgcolor="#b0e0e6"> 
      <td align="center"  width="50"  >번 호</td> 
      <td align="center"  width="250" >제   목</td> 
      <td align="center"  width="100" >작성자</td>
      <td align="center"  width="150" >작성일</td> 
      <td align="center"  width="50" >조 회</td> 
      <td align="center"  width="100" >IP</td>    
    </tr>
    
    <c:forEach var="list" items="${list}">
    
   <tr height="30">
   
    <td align="center"  width="50" >
    <c:out value="${beginPerPage}"/>
    <c:set var="beginPerPage" value="${beginPerPage-1}"/>
    </td>
    
    <td  width="250" >

	<c:if test="${list.re_level>0}">
	  <img src="images/level.gif" width="${list.re_level*7}" height="16">
	  <img src="images/re.gif">
	   </c:if>
	  
	  <c:if test="${list.re_level==0}"> 
	  <img src="images/level.gif" width="0" height="16">
	  </c:if>        
	  
      <a href="/JspBoard2/content.do?num=${list.num}&pageNum=${currentPage}">
           ${list.subject}</a> 
         
         <c:if test="${list.readcount>=20}">
         <img src="images/hot.gif" border="0"  height="16"> 
         </c:if>
         
         </td>
    <td align="center"  width="100"> 
       <a href="mailto:${list.email}">${list.writer}</a></td>
    <td align="center"  width="150">${list.reg_date}</td>
    <td align="center"  width="50">${list.readcount}</td>
    <td align="center" width="100" >${list.ip}</td>
  </tr>
  </c:forEach>
  
</table>
 </c:if>
 

<tr align="center">
<c:if test="${count>0}">
<c:set var="pageCount" value="${count/pageSize+(count%pageSize==0?0:1)}"/>
<c:set var="startPage" value="${currentPage-((currentPage-1)%blockSize)}"/>
<c:set var="endPage" value="${startPage+blockSize-1}"/>
<c:if test="${endPage>pageCount}">
<c:set var="endPage" value="${pageCount}"/>
</c:if>
<c:if test="${startPage>blockSize}">
<a href="/JspBoard2/list.do?pageNum=${startPage-blockSize}">[이전]</a>
</c:if>

<c:forEach var="i" begin="${startPage}" end="${endPage}">

<c:if test="${i==currentPage}"> 
<a href="/JspBoard2/list.do?pageNum=${i}"><b>${i}</b></a>
</c:if>

<c:if test="${i!=currentPage}">
<a href="/JspBoard2/list.do?pageNum=${i}">${i}</a>
</c:if>

</c:forEach>

<c:if test="${endPage<pageCount}">
<a href="/JspBoard2/list.do?pageNum=${startPage+blockSize}">[다음]</a>
</c:if>

 </c:if>
 
  </tr>
</center>
</body>
</html>