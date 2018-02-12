package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//추가
import NIW.*;
import java.util.*;

// /list.do=actionListAction(명령처리클래스=스프링(액션)
public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		// list.jsp(자바소스 코드+화면출력소스코드)
		// 추가 -//  pageNum,search(검색분야),searchtext(검색어)
			String pageNum=request.getParameter("pageNum");
			String select = request.getParameter("select"); //검색분야 문자열
			String search = request.getParameter("search"); //검색문자열
			
			   int count=0;//총레코드수
		   //게시판을 맨 처음 실행시키면 무조건 1페이지 부터 출력
		   if(pageNum==null){
			   pageNum="1";//default
		   }
		   
		   List list=null;//화면에 출력할 레코드데이터
		   BoardDAO dao=new BoardDAO();

		   count=dao.getArticleSearchCount(select, search);//select count(*) from board
		   System.out.println("현재 검색 레코드수(count)=>"+count);
		   
			//추가2 페이징 가져오기
			   HashMap<String,Integer> pgList = dao.pageList(pageNum, count);
			   
		   if(count > 0){
			   System.out.println(pgList.get("startRow")+":::"+pgList.get("endRow"));
			   list=dao.getSearchList(select, search, pgList.get("startRow"), pgList.get("pageSize"));//5개씩 (endRow X)
		   }else {
			   list=Collections.EMPTY_LIST;//아무것도 없다는 표시
		   }
		   
		   request.setAttribute("select", select);
		   request.setAttribute("search", search);
		   request.setAttribute("pgList", pgList); // 페이징처리된것을 전달
		   request.setAttribute("list", list);//${articleList} // 검색리스트를 전달
		   
		   //3.페이지로 forward로 이동
		return "/list.jsp";  //  /board/list.jsp
	}
}



