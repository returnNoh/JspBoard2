package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		
		request.setCharacterEncoding("UTF-8");

		String num = request.getParameter("num");
		String pageNum = request.getParameter("pageNum");


		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		
		
		
		return "/deleteForm.jsp";
	}

}
