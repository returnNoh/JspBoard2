package action;

import javax.servlet.http.*; // HttpServletRequest,HttpServletResponse

//웹상에서 요청이 들어올 때 마다 공통으로 처리해주는 추상메소드

public interface CommandAction { // 스프링에서는 Controller 을 상속받음
	//이동할 페이지의 경로와 페이지명이 필요 -> String(반환값) or ModelAndView(스프링에서 반환값)
	
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable;
	
}
