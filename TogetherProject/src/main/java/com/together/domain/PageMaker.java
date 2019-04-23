package com.together.domain;

// 페이징을 위한 PageMaker 클래스 생성
public class PageMaker {
	private int totalcount; //전체 게시물(회원) 개수
	private int pagenum; //현재 페이지 번호
	private int contentnum = 10; //한 페이지에 몇개 표시할지
	private int startPage = 1; //현재 페이지 블록의 시작 페이지
	private int endPage = 5; //현재 페이지 블록의 마지막 페이지
	private boolean prev = false; //이전 페이지로 가는 화살표, 처음 게시판에 들어갔을때 보이면 안되기때문에 false
	private boolean next; //다음 페이지로 가는 화살표
	private int currentblock; //현재 페이지 블록
	private int lastblock; //마지막 페이지 블록
	//오라클DB 사용을 위한 변수 추가
	private int startNum; //
	private int endNum; //
	

	public int getStartNum() {
		return startNum;
	}

	//컨텐츠 시작번호 설정 -> (현재 페이지-1) * 보여줄 컨텐츠 수 +1
	public void setStartNum(int pagenum) {
		
		this.startNum = (pagenum-1) * this.contentnum + 1;
	}

	public int getEndNum() {
		return endNum;
	}

	//컨텐츠 끝번호 설정 -> 현재 페이지 * 보여줄 컨텐츠 수
	public void setEndNum(int pagenum) {
		this.endNum = pagenum * this.contentnum;
	}
	
	
	
	
	//PageMaker의 모든 매개변수는 컨트롤러에서 다 호출 할 것임
	
	public void prevnext(int pagenum) {
		
		if(pagenum > 0 && pagenum < 6) { //현재 페이지가 첫 번째 페이지 블록 안에 있으면 
			setPrev(false); //이전 페이지로가는 화살표가 보이지 않게 됨
			setNext(true); // 다음 페이지로 가는 화살표는 보이게 됨
		}else if(getLastblock() == getCurrentblock()) { //마지막 페이지 블록과 현재 페이지 블록이 같으면, 즉 마지막 페이지 블록 일때
			setPrev(true); // 이젠 페이지로 가는 화살표가 보임
			setNext(false); // 다음 페이지로 가는 화살표가 사라짐
		}else {
			setPrev(true); //모두 보임
			setNext(true);
		}
	}
	
	
	//이 게시판에 몇 페이지까지 표시할지 필요한 함수
	public int calcpage(int totalcount, int contentnum) {//전체 페이지 수를 계산하는 함수
		//ex)125 (전체게시글 ) / 10 (한 페이지에 표시할 게시글 개수) = 12.5
		// 나머지가 0보다 크기때문에 +1한 후 => 13페이지까지 보여줌
		// ex)50 / 10 => 5
		// 5페이지까지 보여줌

		int totalpage = totalcount / contentnum;
		if(totalcount % contentnum > 0) { //나머지가 0보다 클 때 totalpage에 +1을 함
			totalpage++;
		}
		return totalpage;
	} 
	
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	public int getContentnum() {
		return contentnum;
	}
	public void setContentnum(int contentnum) {
		this.contentnum = contentnum;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int currentblock) {
		this.startPage = (currentblock*5)-4; // ex) 1 * 5 - 4 = 1 => 1페이지 블록의 첫 페이지 1
		//페이지 블록이 1 일때 : 1 2 3 4 5
		//페이지 블록이 2 일때 : 6 7 8 9 10
		//페이지 블록이 3 일때 : 11 12 13
		
		//10개씩 보여주고 싶으면 *10 -9를 하면 됨
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int getlastblock, int getcurrentblock) {
		if(getlastblock == getcurrentblock) { //마지막 페이지 블록 번호 == 현재 페이지 블록 번호가 같으면
			this.endPage = calcpage(getTotalcount(), getContentnum()); //
		}
		else {
			this.endPage = getStartPage()+4; // 한페이지 블록당 페이지를 5개씩 출력하기 때문에 시작페이지 +4
		}
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getCurrentblock() {
		return currentblock;
	}
	public void setCurrentblock(int pagenum) {
		//페이지 번호를 통해서 구한다
		// ex) 현재 페이지 번호가 1 페이지일 경우
		// => 페이지 번호 / 페이지 그룹 안의 페이지 개수
		// 1 / 5 => 0.2-> 0 => 0 + 1 => 페이지 블록 1
		
		// ex) 현재 페이지 번호가 3 페이지일 경우
		// 3 / 5 => 0.xx => 0 + 1 => 페이지 블록 1
		
		// ex) 현재 페이지 번호가 8 페이지일 경우
		// 8 / 5 => 1.6x => 1 + 1 => 페이지 블록 2
		
		this.currentblock = pagenum/5;
		if(pagenum%5>0) {
			this.currentblock++;
		}
	}
	public int getLastblock() {
		return lastblock;
	}
	public void setLastblock(int lastblock) {
		//회원정보를 10개씩 가져옴, 1블록당 5개 페이지
		//10, 5 => 10 * 5 => 50
		
		//전체 회원수 125 / 50 => 2.5 => 2 + 1
		//페이지 블록 3
		this.lastblock = totalcount / (5*this.contentnum);
		if(totalcount %(5*this.contentnum) > 0) {
			this.lastblock++;
		}
	}
}
