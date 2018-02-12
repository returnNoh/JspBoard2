package action;

import javax.servlet.http.HttpServletRequest;
import NIW.*;
import javax.servlet.http.HttpServletResponse;

public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String passwd = request.getParameter("passwd");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		BoardDAO dao = new BoardDAO();

		boolean check = dao.boardDelete(passwd, num);
		
		request.setAttribute("check", check);
		request.setAttribute("pageNum", pageNum);
		
		
		return "/deletePro.jsp";
	}

}
