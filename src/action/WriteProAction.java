package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import NIW.*;

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		BoardDTO dto = new BoardDTO();
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setWriter(request.getParameter("writer"));
		dto.setEmail(request.getParameter("email"));
		dto.setSubject(request.getParameter("subject"));
		dto.setPasswd(request.getParameter("passwd"));
		
		dto.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		dto.setRef(Integer.parseInt(request.getParameter("ref")));
		dto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		dto.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		dto.setContent(request.getParameter("content"));
		
		
		dto.setIp(request.getRemoteAddr());
		
		//<jsp:setProperty property="*" name="dto"/>

		
		System.out.println("writePro 들어옴");
		
		BoardDAO dao = new BoardDAO();

		dao.insertArticle(dto);
		
		
		
		return "/writePro.jsp";
	}

}
