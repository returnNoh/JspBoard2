package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import NIW.*;
import javax.servlet.http.HttpServletResponse;

public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		
		request.setCharacterEncoding("UTF-8");

		// num , writer , subject , email , content , passwd

		
		BoardDTO dto = new BoardDTO();
		
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setWriter(request.getParameter("writer"));
		dto.setEmail(request.getParameter("email"));
		dto.setSubject(request.getParameter("subject"));
		dto.setPasswd(request.getParameter("passwd"));
				
		dto.setContent(request.getParameter("content"));
		
		
		BoardDAO dao = new BoardDAO();
		String pageNum = request.getParameter("pageNum");
		boolean check = dao.boardUpdate(dto);

		request.setAttribute("pageNum", pageNum);
		
		
		return "/updatePro.jsp";
	}

}
