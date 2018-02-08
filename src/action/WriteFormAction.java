package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		
		// list.do -> 신규글 , content.do -> 답변글
		//신규글의 경우. ( list.do 에서 넘어온 경우 )
	 int num = 0,ref=1,re_step=0,re_level=0; // writePro.jsp 전달 -> 이 경우 신규글
		//답변글의 경우. ( content.jsp 에서 넘어온 경우 )
		if(request.getParameter("num")!=null){
			num=Integer.parseInt(request.getParameter("num"));
			ref=Integer.parseInt(request.getParameter("ref"));
			re_step=Integer.parseInt(request.getParameter("re_step"));
			re_level=Integer.parseInt(request.getParameter("re_level"));
			System.out.println("답변인가 신규인가 :"+num+re_step);
		}
		// 액션클래스가 처리한 결과값들을 뷰가 받을 수 있게 속성값 지정
		request.setAttribute("num", num);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level",	re_level);
		
		
		return "/writeForm.jsp";
	}

}
