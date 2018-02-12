package NIW;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class BoardDAO {
		
	private DBConnectionMgr pool;
	
	public BoardDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
			System.out.println("pool");
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	//3. 페이징 처리(페이지별로 화면에 보여주는 레코드수를 조절)
	//1) 전체 레코드수를 구해와야 한다. => select count(*) from board;
	
	public int getArticleCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "select count(*) from board";
		
		try {
			
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
			count = rs.getInt(1);
			}
			System.out.println("총 레코드 수 :"+count);
			
		}catch(Exception e) {
			System.out.println("getArticleCount 실패"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return count;
	}
	
	//(1) 게시판의 레코드 수를 검색어에 따른 메소드(검색분류 , 검색문자열)
	
	public int getArticleSearchCount(String select,String search) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql ="select count(*) from board";
		if(select!=null) {
			if(select.equals("subject_content")) {
				sql = "select count(*) from board where subject like '%"+search+"%' or content like '%"+search+"%' ";
			}else sql = "select count(*) from board where "+select+" like '%"+search+"%'";
		}
		try {
			System.out.println(sql);
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
			count = rs.getInt(1);
			}
			System.out.println("총 레코드 수 :"+count);
			
		}catch(Exception e) {
			System.out.println("getArticleCount 실패"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return count;	
	}
	
	// (2) 검색어에 따른 게시판 목록 물러오기
	public ArrayList<BoardDTO> getBoardArticles(String select,String search,int start,int end) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;
		ArrayList<BoardDTO> zipcode = new ArrayList(end); // 생성자로 초기 공간을 지정
		BoardDTO code ;
		String sql="";
		if(select==null) {
			sql = "select  * from board order by ref desc,re_step asc limit "+(start)+","+end;
		}else {
			if(select.equals("subject_content")) {
				sql = "select  * from board where subject like '%"+search+"%' or content like '%"+search
				+"%' order by ref desc,re_step asc limit "+(start)+","+end;
			}
			else {
				sql = "select  * from board where "+select+" like '%"+search
						+"%' order by ref desc,re_step asc limit "+(start)+","+end;
			}
		}
		System.out.println("getBoard()2"+sql);
		
		try {
			con=pool.getConnection();
			pstmt=con.prepareStatement(sql);
			
			System.out.println(sql);
			System.out.println(rs=pstmt.executeQuery());
			if(rs.next()) {
			do {
				
				code = new BoardDTO();
				code.setNum(rs.getInt("num"));
				code.setWriter(rs.getString("writer"));
				code.setEmail(rs.getString("email"));
				code.setSubject(rs.getString("subject"));
				code.setContent(rs.getString("content"));
				code.setPasswd(rs.getString("passwd"));
				code.setReg_date(rs.getTimestamp("reg_date"));
				code.setReadcount(rs.getInt("readcount"));
				code.setIp(rs.getString("ip"));
				code.setRef(rs.getInt("ref"));
				code.setRe_step(rs.getInt("re_step"));
				code.setRe_level(rs.getInt("re_level"));
				zipcode.add(code);
				
			}
			while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("게시글 정보불러오기 실패"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return zipcode;
	}

	
	//(3) 페이징 변수들을 전달하는 메소드
	public HashMap pageList(String pageNum,int count) {
		
		
		
		//페이징 처리 결과를 저장할 HashMap객체 생성
		HashMap<String,Integer> pglist = new HashMap<String,Integer>();
		//==== 페이징 처리 계산부분 복사===================================
		
		//1 현재페이지
		System.out.println("pageNum=>"+pageNum);
		if(pageNum==null)pageNum="1";
		int currentPage = Integer.parseInt(pageNum); // 숫자 현재페이지
		
		//3 보여줄 단위 개수 (레코드 , 페이지 , 블록 ) 설정
				int pageSize = 5;         // 한페이지당 보여주는 레코드갯수    
			    int blockSize = 5;    //한 블록당 보여주는 페이지의 수
		//2 전체 레코드 개수 확인
		//int count = count;
			    System.out.println("개수 체크용 "+count);
		// DB상의 레코드 시작 번호 limit 수치
		int startRow = (currentPage-1)*pageSize;
		int endRow = currentPage*pageSize;
		
		//4 전체 레코드 수를 참고하여 전체 페이지, 블록 설정
	    // beginPerPage  계산  (페이지별게시물 번호 제일 높은것)
		int beginPerPage = count-(currentPage-1)*pageSize;
		
		System.out.println(count+":count |||"+beginPerPage+":beginPerPage");
		
		int pageCount = count/pageSize+(count%pageSize==0?0:1); // 전체 페이지 개수2 둘다 똑같음  
		//int totalBlock=(int)Math.ceil((double)totalPage/blockSize);		//전체 블록 개수
		int blockCount = 0;
		//시작페이지 , 끝페이지
		int startPage = 0;
		//10의 배수인지
		if(currentPage%blockSize!=0){ // 1~9 , 11~19 , 21~22 
			startPage = currentPage/blockSize*blockSize+1;
		}else{ //10,20,30,40
			
			startPage=((currentPage/blockSize)-1)*blockSize+1;
			
		}
		int endPage = startPage+blockSize-1;
		if(endPage>pageCount)	endPage=pageCount;
		
		//-- ListAction(pageNun,count) => pageList를 넘기고,, -> ListAction -> list.jsp
		
		
		
		
		
		pglist.put("currentPage", currentPage);
		
		pglist.put("pageSize", pageSize);
		pglist.put("blockSize", blockSize);
		
		pglist.put("startRow", startRow);
		pglist.put("endRow", endRow);
		
		pglist.put("pageCount", pageCount);
		pglist.put("blockCount", blockCount);
		
		pglist.put("startPage", startPage);
		pglist.put("endPage", endPage);
		
		pglist.put("beginPerPage", beginPerPage);


		
		//number=count-(currentPage-1)*pageSize;
		//request.setAttribute(키명,저장할값)// 이용해서 값 공유
	    // beginPerPage  계산  (페이지별게시물 번호 제일 높은것)
		//=======================================================
		return pglist;
		
		 
	}
	
	
	
	
	
	
	//2) 글목록보기에 대한 메소드 구현(범위)
	//select * from board order by ref desc,re_step asc limit ?,?
	//start -> 레코드의 시작번호 end -> 웹상에 보여줄 레코드 번호 // 1-10 11-10 21-10
	public ArrayList<BoardDTO> getArticles(int start,int end) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;
		ArrayList<BoardDTO> zipcode = new ArrayList(end); // 생성자로 초기 공간을 지정
		BoardDTO code ;
		
		String sql = "select  * from board order by ref desc,re_step asc limit "+(start)+","+end;
		try {
			con=pool.getConnection();
			pstmt=con.prepareStatement(sql);
			
			System.out.println(sql);
			System.out.println(rs=pstmt.executeQuery());
			if(rs.next()) {
			do {
				
				code = new BoardDTO();
				code.setNum(rs.getInt("num"));
				code.setWriter(rs.getString("writer"));
				code.setEmail(rs.getString("email"));
				code.setSubject(rs.getString("subject"));
				code.setContent(rs.getString("content"));
				code.setPasswd(rs.getString("passwd"));
				code.setReg_date(rs.getTimestamp("reg_date"));
				code.setReadcount(rs.getInt("readcount"));
				code.setIp(rs.getString("ip"));
				code.setRef(rs.getInt("ref"));
				code.setRe_step(rs.getInt("re_step"));
				code.setRe_level(rs.getInt("re_level"));
				zipcode.add(code);
				
			}
			while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("게시글 정보불러오기 실패"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return zipcode;
	}
	
	
	
	// 글 상세보기
		public BoardDTO getContent(int num) {
		
			System.out.println("getContent메소드 시작");
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BoardDTO dto = null;
			
			
			String sql = "select  * from board where num="+num;
			try {
			
				con=pool.getConnection();
				
				sql ="update board set readcount=readcount+1 where num="+num;
				pstmt=con.prepareStatement(sql);
				System.out.println(pstmt.executeUpdate()+"조회수 증가");
				
				//pstmt.close(); // 원칙적으로는 닫고 새로 연결객체를 넣어야 한다.
				
				sql = "select  * from board where num="+num;
				pstmt=con.prepareStatement(sql);
				
				System.out.println(sql);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					dto = makeContentFromResult(rs);
//					dto= new BoardDTO();
//					dto.setNum(rs.getInt("num"));
//					dto.setWriter(rs.getString("writer"));
//					dto.setEmail(rs.getString("email"));
//					dto.setSubject(rs.getString("subject"));
//					dto.setContent(rs.getString("content"));
//					dto.setPasswd(rs.getString("passwd"));
//					dto.setReg_date(rs.getTimestamp("reg_date"));
//					dto.setReadcount(rs.getInt("readcount"));
//					dto.setIp(rs.getString("ip"));
//					dto.setRef(rs.getInt("ref"));
//					dto.setRe_step(rs.getInt("re_step"));
//					dto.setRe_level(rs.getInt("re_level"));
				}
			
	}catch(Exception e) {
		System.out.println("게시글 정보불러오기 실패"+e);
	}finally {
		pool.freeConnection(con,pstmt,rs);
	}
			return dto;
	
}	
		
		
		
		//글작성
		public void insertArticle(BoardDTO dto) {
					//반환값 없는것으로 만들어보기
			System.out.println("boardWrite (new) 메소드 시작");
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null; // 강사님 설계 -> select max해서 넘버에 넣는설계라서 ResultSet객체가 필요
			
			int num = dto.getNum(); // 0이라면 신규글
			int ref = dto.getRef();
			int re_step = dto.getRe_step();
			int re_level = dto.getRe_level()+1;
			int new_num =0;
			
			String sql = "insert into board values(null,?,?,?,?,?,?,?,?,?,?,?)"; // 신규라면 기본값이 0인것은 0으로 지정해도됨.
			sql = "select max(num) from board";
			try {
			
				con=pool.getConnection();
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {//글번호 최대값찾기
					new_num = rs.getInt(1)+1;
				}else new_num=1;
				
				//주석처리한것은  DB상에서 re_step을 asc로 정렬했을때 똑같이 나오게 하는 게시글 작성구문이다. ( re_step 수치가 역순으로 들어감 )
//				if(rs.next()) {
//				
//				sql = "update board set re_step=re_step+1 where ref="+ref+"and re_step>"+re_step;
//				pstmt=con.prepareStatement(sql);
//				System.out.println(pstmt.executeUpdate());

				
				if(num!=0) {//답변글
					//sql = "select max(re_step) from board where ref="+ref+" and re_level="+re_level; 
					//pstmt=con.prepareStatement(sql);
					//rs=pstmt.executeQuery();
					//if(rs.next()) {
						//re_step=rs.getInt(1)+1;
						sql = "update board set re_step=re_step+1 where ref="+ref+" and re_step>"+re_step;
						System.out.println(sql);
						pstmt=con.prepareStatement(sql);
						System.out.println(pstmt.executeUpdate());
						re_step++;
					//} // 설계상 답변글 썼을때 답변글의 y 축위치 re_step위치가 제일 위에 위치하는가 아래에 위치하는가 차이?
					
					
				}else {//신규글
					ref=new_num;
					re_step=0;
					re_level=0;
				}
				
				
				sql = "insert into board values(?,?,?,?,?,?,?,?,?,?,?,?)"; // 타임스탬프에 now()를 넣어서 DB에서 값을 넣게 할 수 있다.
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,new_num);
				pstmt.setString(2, dto.getWriter());
				pstmt.setString(3, dto.getEmail());
				pstmt.setString(4, dto.getSubject());
				pstmt.setString(5, dto.getPasswd());
				pstmt.setTimestamp(6, dto.getReg_date());
				pstmt.setInt(7, 0); 
				//---------------- 신규글과 답변글의 수치가 다른 값
				pstmt.setInt(8, ref); 
				pstmt.setInt(9, re_step); 
				pstmt.setInt(10, re_level); 
				//----------------
				pstmt.setString(11, dto.getContent());
				pstmt.setString(12, dto.getIp());
						
				System.out.println(sql);
				
				System.out.println(pstmt.executeUpdate());
	}catch(Exception e) {
		System.out.println("게시글 작성 실패"+e);
	}finally {
		pool.freeConnection(con,pstmt,rs);
	}
		}
	
		
		
		//업데이트 메소드를 클래스 내부에서만 사용하는 형태로 사용하는경우를 생각하면 ..
		//BoardDTO를 반환하는 메소드 만들어서 코드 재사용
		private BoardDTO makeContentFromResult(ResultSet rs) throws Exception{
		BoardDTO dto= new BoardDTO();
		dto.setNum(rs.getInt("num"));
		dto.setWriter(rs.getString("writer"));
		dto.setEmail(rs.getString("email"));
		dto.setSubject(rs.getString("subject"));
		dto.setContent(rs.getString("content"));
		dto.setPasswd(rs.getString("passwd"));
		dto.setReg_date(rs.getTimestamp("reg_date"));
		dto.setReadcount(rs.getInt("readcount"));
		dto.setIp(rs.getString("ip"));
		dto.setRef(rs.getInt("ref"));
		dto.setRe_step(rs.getInt("re_step"));
		dto.setRe_level(rs.getInt("re_level"));
		
		return dto;
		
		}
		
		public boolean boardUpdate(BoardDTO dto) {
			
			
			System.out.println("getContent메소드 시작");
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			boolean check = false;
			int check2 = 0;
			// num , writer , subject , email , content , passwd
			// 암호 먼저 체크 후 업데이트 하는 형식으로 해도 될것같음.
			String sql = "";
			try {
				
				
			
				con=pool.getConnection();
				sql = "select num from board where passwd=?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, dto.getPasswd());
				System.out.println();
				rs=pstmt.executeQuery();
				if(rs.next()) {
				
				
				sql ="update board set writer=?,subject=?,email=?,content=? where num="+dto.getNum();
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, dto.getWriter());
				pstmt.setString(2, dto.getSubject());
				pstmt.setString(3, dto.getEmail());
				pstmt.setString(4, dto.getContent());
				
				
				if(pstmt.executeUpdate()>0) {
					check=true;
				}
				}
			
	}catch(Exception e) {
		System.out.println("게시글 정보불러오기 실패"+e);
	}finally {
		pool.freeConnection(con,pstmt);
	}
			return check;
		}
		
		
		
		
		
		
		public boolean boardDelete(String passwd,int num) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Boolean check=false;
			BoardDTO dto = null;
			
			String sql = "select num from board where passwd='"+passwd+"'";
			try {
			
				con=pool.getConnection();
				pstmt=con.prepareStatement(sql);
				
				System.out.println(sql);
				System.out.println(rs=pstmt.executeQuery());
				if(rs.next()) {
					sql="delete from board where num="+num;
					pstmt=con.prepareStatement(sql);
					if(pstmt.executeUpdate()>0) {
						System.out.println("삭제 성공");
						check=true;
					}
					System.out.println("삭제 실패");
				}System.out.println("비밀번호가 틀립니다.");
			}catch(Exception e) {
				System.out.println("게시글 삭제 실패"+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}

			
			return check;
		}
		
		
		public BoardDTO getUpdateContent(int num) {
			
			System.out.println("getContent메소드 시작");
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BoardDTO dto = null;
			
			
			String sql = "select  * from board where num="+num;
			try {
			
				con=pool.getConnection();

				//pstmt.close(); // 원칙적으로는 닫고 새로 연결객체를 넣어야 한다.
				
				pstmt=con.prepareStatement(sql);
				
				System.out.println(sql);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					dto = makeContentFromResult(rs);
//					dto= new BoardDTO();
//					dto.setNum(rs.getInt("num"));
//					dto.setWriter(rs.getString("writer"));
//					dto.setEmail(rs.getString("email"));
//					dto.setSubject(rs.getString("subject"));
//					dto.setContent(rs.getString("content"));
//					dto.setPasswd(rs.getString("passwd"));
//					dto.setReg_date(rs.getTimestamp("reg_date"));
//					dto.setReadcount(rs.getInt("readcount"));
//					dto.setIp(rs.getString("ip"));
//					dto.setRef(rs.getInt("ref"));
//					dto.setRe_step(rs.getInt("re_step"));
//					dto.setRe_level(rs.getInt("re_level"));
				}
			
	}catch(Exception e) {
		System.out.println("게시글 정보불러오기 실패"+e);
	}finally {
		pool.freeConnection(con,pstmt,rs);
	}
			return dto;
	
}	

		
		
		
			
}

	
