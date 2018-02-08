package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import NIW.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class ListAction implements CommandAction {
	//view=com.requestPro(request, response); ->  /list.do -> /list.jsp
	

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		// 1. jsp가 처리했던 소스코드를 액션클래스가 처리 -> 메소드호출해서 DB연동까지 하고 결과값 얻어오고
		// 2. request.setAttribute(키명,저장할값); 
		// 3. 정해진 view 페이지로 이동
		


		request.setCharacterEncoding("UTF-8");
		BoardDAO dao = new BoardDAO();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		//1 현재페이지
		String PageNum = request.getParameter("pageNum"); // 문자열 현재페이지
		if(PageNum==null)PageNum="1";
		int currentPage = Integer.parseInt(PageNum); // 숫자 현재페이지
		

		//3 보여줄 단위 개수 (레코드 , 페이지 , 블록 ) 설정
				int pageSize = 5;         // 한페이지당 보여주는 레코드갯수    
			    int blockSize = 5;    //한 블록당 보여주는 페이지의 수
		
		//2 전체 레코드 개수 확인
		int count = dao.getArticleCount(); // 레코드개수
			    System.out.println("개수 체크용 "+count);
		// DB상의 레코드 시작 번호 limit 수치
		int startRow = (currentPage-1)*pageSize;
		// 
		int endRow = currentPage*pageSize;
		
		//4 전체 레코드 수를 참고하여 전체 페이지, 블록 설정
	
		
		
	    // beginPerPage  계산  (페이지별게시물 번호 제일 높은것)
		int beginPerPage = count-(currentPage-1)*pageSize;
		
		//number=count-(currentPage-1)*pageSize;
		
		
		ArrayList<BoardDTO> list = null;
		if(count>0){
		list = dao.getArticles(startRow, pageSize);
		}
		BoardDTO dto = null;
			
		
		//request.setAttribute(키명,저장할값)// 이용해서 값 공유
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("startRow", startRow);
		request.setAttribute("list", list);
		request.setAttribute("beginPerPage", beginPerPage);

		
		
		return "/list.jsp";
	}

}
