package NIW;

import java.sql.Timestamp;

public class BoardDTO {
		//분류필드 -> 공지,자유,답변 등..
	private int num; // 게시물 구분번호(article)
	private String writer;//작성 
	private String subject; // 글제목 -> 글 상세보기로 연결
	private String email; // 이메일
	private String content; // 내용  오라클 -> varchar2(4000)   // mysql -> text
	private String passwd; // 게시물암호
	
	private Timestamp reg_date; // 작성날짜  오라클 -> sysdate  //  mysql -> now()
	
	private int readcount; // 조회수 -> default 0
	private String ip; // 작성자의 ip 주소출력 -> request.getRemoteAddr()
	//여기까지가 자유게시판  대댓글 , 답게시글 없는 게시판
	//댓글다는필드 추가 3개
	private int ref; // 글 그룹 번호 (=게시물 번호)
	private int re_step;//답변글의 순서(=같은 그룹일 때의 답변글 순서)
	private int re_level; // depth (=깊이) -> 들여쓰기 (답변글의 답변에 대한 깊이)
	//+ 파일업로드(자료실)
	//private String file; // 파일의 정보 추가 (용량제한이 필요하면 size도 따로,) 
	//업로드할 파일이 여러개라면 -> 테이블 추가
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
}
