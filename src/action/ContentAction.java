package action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import NIW.*;

public class ContentAction implements CommandAction {

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

BoardDTO dto = dao.getContent(num);



request.setAttribute("num", num); // dto객체안에 있으므로 안넘겨도 되긴하는데 그냥 넘겨봄
request.setAttribute("pageNum", pageNum);
request.setAttribute("dto",dto);



		
		return "/content.jsp";
	}

}
