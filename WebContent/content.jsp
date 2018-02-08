<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>


<body bgcolor="#e0ffff" onload="setting()">  
<center><b>글내용 보기</b>
<br>
<form>
<table width="500" border="1" cellspacing="0" cellpadding="0"  bgcolor="#e0ffff" align="center">  
  <tr height="30">
    <td align="center" width="125" bgcolor="#b0e0e6">글번호</td>
    <td align="center" width="125" align="center">
	     ${num}</td>
    <td align="center" width="125" bgcolor="#b0e0e6">조회수</td>
    <td align="center" width="125" align="center">
	     ${dto.readcount}</td>
  </tr>
  <tr height="30">
    <td align="center" width="125" bgcolor="#b0e0e6">작성자</td>
    <td align="center" width="125" align="center">
	     ${dto.writer}</td>
    <td align="center" width="125" bgcolor="#b0e0e6" >작성일</td>
    <td align="center" width="125" align="center">
	     ${dto.reg_date}</td>
  </tr>
  <tr height="30">
    <td align="center" width="125" bgcolor="#b0e0e6">글제목</td>
    <td align="center" width="375" align="center" colspan="3">
	     ${dto.subject}</td>
  </tr>
  <tr>
    <td align="center" width="125" bgcolor="#b0e0e6">글내용</td>
    <td align="left" width="375" colspan="3"><pre>${dto.content}</pre></td> 
    <!-- pre태그는 줄바꿈(br)을 따로 안해도 되게 원본 그대로 (줄바꿈까지 포함해서) 표시하게 해준다.-->
  </tr>
  
  <tr height="30">      
    <td colspan="4" bgcolor="#b0e0e6" align="right" > 
	  <input type="button" value="글수정" 
       onclick="document.location.href='/JspBoard2/updateForm.do?num=${num}&pageNum=${pageNum}'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
	  <input type="button" value="글삭제" 
       onclick="document.location.href='/JspBoard2/deleteForm.do?num=${num}&pageNum=${pageNum}'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value="답글쓰기" 
       onclick="document.location.href='/JspBoard2/writeForm.do?num=${num}&ref=${dto.ref}&re_step=${dto.re_step}&re_level=${dto.re_level }'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="글목록" 
       onclick="document.location.href='/JspBoard2/list.do?pageNum=${pageNum}'">
    </td>
  </tr>
</table>    
</form>      
</body>
</html>      
