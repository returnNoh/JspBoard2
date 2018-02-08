<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>

<body bgcolor="#e0ffff">  
<center><b>글쓰기</b>
<br>
<form method="post" name="writeForm" action="/JspBoard2/writePro.do" onsubmit="return writeSave()" >
												<!-- onsubmit=' return 함수명' 에서 함수가 true가 아니라면 submit이 실행되지 않음 -->
	<input type="hidden" name="num" value="${num}">
     <input type="hidden" name="ref" value="${ref}"> 
     <input type="hidden" name="re_step" value="${re_step}"> 
     <input type="hidden" name="re_level" value="${re_level}"> 
     
<table width="400" border="1" cellspacing="0" cellpadding="0"  bgcolor="#e0ffff" align="center">
   <tr>
    <td align="right" colspan="2" bgcolor="#b0e0e6">
	    <a href="list.jsp"> 글목록</a> 
   </td>
   </tr>
   <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center">이 름</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="writer"></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center" >제 목</td>
    <td  width="330">
       <input type="text" size="40" maxlength="50" name="subject"></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center">Email</td>
    <td  width="330">
       <input type="text" size="40" maxlength="30" name="email" ></td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center" >내 용</td>
    <td  width="330" >
     <textarea name="content" rows="13" cols="40"></textarea> </td>
  </tr>
  <tr>
    <td  width="70"  bgcolor="#b0e0e6" align="center" >비밀번호</td>
    <td  width="330" >
     <input type="password" size="8" maxlength="12" name="passwd"> 
      
	 </td>
  </tr>
<tr>      
 <td colspan=2 bgcolor="#b0e0e6" align="center"> 
  <input type="submit" value="글쓰기" >  
  <input type="reset" value="다시작성">
  <input type="button" value="목록보기" OnClick="window.location='/JspBoard2/list.do'">
</td></tr></table>    
</form>      
</body>
</html>      