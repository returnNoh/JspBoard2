package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import NIW.BoardDAO;
import NIW.BoardDTO;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		

BoardDAO dao = new BoardDAO();
//System.out.println(request.getParameter("num"));

int num = 0;
if(request.getParameter("num")!=null)
num = Integer.parseInt(request.getParameter("num"));
int pageNum=0;
if(request.getParameter("pageNum")!=null)
pageNum =Integer.parseInt(request.getParameter("pageNum"));

BoardDTO dto = dao.getUpdateContent(num);



request.setAttribute("pageNum", pageNum);
request.setAttribute("dto",dto);
		
		
		return "/updateForm.jsp";
	}

}
