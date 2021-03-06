<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="NIW.*,java.util.*,java.text.SimpleDateFormat"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//HashtaMap paList = (HashMap) request.getAttribute("pgList");
	//pgList.get("count") = > ${pgList.count}
	//System.out.println("이거맞나"+request.getAttribute("list"));
	
	
%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#e0ffff">
<center><b>글목록(전체 글:${pgList.beginPerPage})</b>
<table width="700">
<tr>
    <td align="right" bgcolor="#b0e0e6">
    <a href="/JspBoard2/writeForm.do">글쓰기</a>
    </td>
</table>
<c:if test="${pgList.count==0}">
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
<tr>
<td align="center">게시판에 저장된 글이 없습니다.</td>
</tr>
</table>
</c:if>


<c:if test="${pgList.beginPerPage>0}">
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30" bgcolor="#b0e0e6"> 
      <td align="center"  width="50"  >번 호</td> 
      <td align="center"  width="250" >제   목</td> 
      <td align="center"  width="100" >작성자</td>
      <td align="center"  width="150" >작성일</td> 
      <td align="center"  width="50" >조 회</td> 
      <td align="center"  width="100" >IP</td>    
    </tr>
    
    
    <c:set var="beginPerPage" value="${pgList.beginPerPage}"/>
    <c:forEach var="list" items="${list}">
    
   <tr height="30" onmouseover="this.style.backgroundColor='blue'" onmouseout="this.style.backgroundColor='#e0ffff'">
   
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
	  
      <a href="/JspBoard2/content.do?num=${list.num}&pageNum=${pgList.currentPage}">
           ${list.subject}</a> 
         
         <c:if test="${list.readcount>=20}">
         <img src="images/hot.gif" border="0"  height="16"> 
         </c:if>
         
         </td>
    <td align="center"  width="100"> 
       <a href="mailto:${list.email}">${list.writer}</a></td>
    <td align="center"  width="150">
    <fmt:formatDate value="${list.reg_date}" timeStyle="medium" pattern="yy.MM.dd (hh:mm)"/>
    </td>
    <td align="center"  width="50">${list.readcount}</td>
    <td align="center" width="100" >${list.ip}</td>
  </tr>
  </c:forEach>
  
</table>
 </c:if>
<tr align="center">

<c:if test="${pgList.startPage>pgList.blockSize}">
<a href="/JspBoard2/list.do?pageNum=${pgList.startPage-pgList.blockSize}&select=${select}&search=${search}">[이전]</a>
</c:if>

<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">

<c:if test="${i==pgList.currentPage}"> 
<a href="/JspBoard2/list.do?pageNum=${i}&select=${select}&search=${search}">
<font color="red>"><b>[${i}]</b></font>
</a>
</c:if>

<c:if test="${i!=pgList.currentPage}">
<a href="/JspBoard2/list.do?pageNum=${i}&select=${select}&search=${search}">${i}</a>
</c:if>

</c:forEach>

<c:if test="${pgList.endPage<pgList.pageCount}">
<a href="/JspBoard2/list.do?pageNum=${pgList.startPage+pgList.blockSize}&select=${select}&search=${search}">[다음]</a>
</c:if>

  </tr>
  
    <p>
  <form name="test" method="get" action="/JspBoard2/list.do">
<tr>
<td>
<select name="select">
<option value="subject"> 글 제목 </option>
<option value="subject_content"> 제목+본문 </option>
<option value="writer"> 작성자 </option>
</select>
</td>
<td>
<input type="text" name="search" size="15" ><input type="button" value="검색" onclick="search()">
</td>


</tr>
</form>
  
</center>
</body>
</html>