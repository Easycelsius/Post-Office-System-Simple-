package mail.model;

import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.Stack;

import mail.db.InternationalMailStock;
import mail.db.LocalMailStock;
import mail.db.MailStock;
import mail.db.RecordInternationalMail;
import mail.db.RecordLocalMail;
import mail.db.UndefinedMail;
import mail.dto.InternationalMail;
import mail.dto.LocalMail;
import mail.dto.MailStockDto;

public class MailProjectModel {

	private static MailProjectModel service = new MailProjectModel();
	private MailStock externalMail = MailStock.getInstance();
	private LocalMailStock LocalMailStore = LocalMailStock.getInstance();
	private InternationalMailStock InternationalMailStore = InternationalMailStock.getInstance();
	private UndefinedMail UndefinedStore = UndefinedMail.getInstance();
	private RecordLocalMail LocalMailRecordStore = RecordLocalMail.getInstance();
	private RecordInternationalMail InternationalMailRecordStore = RecordInternationalMail.getInstance();
	
	private MailProjectModel(){}
	
	public static MailProjectModel getInstance(){
		return service;
	}
	
	// 우편 고유 번호 생성기 - 각 DB의 길이들의 합을 통해 전체 메일 갯수 + 1이 되도록 지정
	public int makeNumber() {
		//각 DB를 돌면서 가장 큰 수 찾기
		int max = 0;
		
		for(int index=0; index<externalMail.getMailStockList().size(); index++) {
			if(max < externalMail.getMailStockList().get(index).getNumber()) {
				max = externalMail.getMailStockList().get(index).getNumber();
			}
		}
		
		ArrayList<LocalMail> list = new ArrayList<LocalMail> (LocalMailStore.getAllLocalMail());
		for(int index=0; index<list.size(); index++) {
			if(max < list.get(index).getNumber()) {
				max = list.get(index).getNumber();
			}
		}
		
		ArrayList<InternationalMail> list2 = new ArrayList<InternationalMail> (InternationalMailStore.getAllInternationalMail());
		for(int index=0; index<list2.size(); index++) {
			if(max < list2.get(index).getNumber()) {
				max = list2.get(index).getNumber();
			}
		}
		
		for(int index=0; index<UndefinedStore.getUndefinedMailBox().size(); index++) {
			if(max < UndefinedStore.getUndefinedMailBox().get(index).getNumber()) {
				max = UndefinedStore.getUndefinedMailBox().get(index).getNumber();
			}
		}
		
		for(int index=0; index<LocalMailRecordStore.getRecordLocalMaiBox().size(); index++) {
			if(max < LocalMailRecordStore.getRecordLocalMaiBox().get(index).getNumber()) {
				max = LocalMailRecordStore.getRecordLocalMaiBox().get(index).getNumber();
			}
		}
		
		for(int index=0; index<InternationalMailRecordStore.getRecordInternationalMaiBox().size(); index++) {
			if(max < InternationalMailRecordStore.getRecordInternationalMaiBox().get(index).getNumber()) {
				max = InternationalMailRecordStore.getRecordInternationalMaiBox().get(index).getNumber();
			}
		}
		
		return max+1;
	}

	// 우편 고유 번호로 검색하는 기능(외부 메일함) -> 일치하는 번호가 있다면 stack의 인덱스를 반환 해준다. 없으면 -1을 반환.
	public int searchByNumber(int number) {
		for(int index=0; index<externalMail.getMailStockList().size(); index++) {
			if(externalMail.getMailStockList().get(index).getNumber() == number) {
				return index;
			} 
		}
		return -1;
	}
	
	// 우편 고유 번호로 검색하는 기능(대기 중인 국내 우편) -> 일치하는 번호가 있다면 인덱스를 반환 해준다. 없으면 -1을 반환.
	public int searchByNumberLocal(int number) {
		ArrayList<LocalMail> list = new ArrayList<LocalMail> (LocalMailStore.getAllLocalMail()); // LinkedList를 ArrayList로 반환해준다. LinkedList는 인덱스를 얻을 수 없기 때문임
		for(int index=0; index<list.size(); index++) {
			if(list.get(index).getNumber() == number) {
				return index;
			} 
		}
		return -1;
	}
	
	// 우편 고유 번호로 검색하는 기능(대기 중인 국제 우편) -> 일치하는 번호가 있다면 인덱스를 반환 해준다. 없으면 -1을 반환.
	public int searchByNumberInternational(int number) {
		ArrayList<InternationalMail> list = new ArrayList<InternationalMail> (InternationalMailStore.getAllInternationalMail()); // LinkedList를 ArrayList로 반환해준다. LinkedList는 인덱스를 얻을 수 없기 때문임
		for(int index=0; index<list.size(); index++) {
			if(list.get(index).getNumber() == number) {
				return index;
			}
		}
		return -1;
	}
	
	// 우편 고유 번호로 검색하는 기능(대기 중인 미분류 우편) -> 일치하는 번호가 있다면 인덱스를 반환해준다. 없으면 -1을 반환.
	public int searchByNumberUndefined(int number) {
		for(int index=0; index<UndefinedStore.getUndefinedMailBox().size(); index++) {
			if(UndefinedStore.getUndefinedMailBox().get(index).getNumber() == number) {
				return index;
			} 
		}
		return -1;
	}
	
	// 우편 고유 번호로 검색하는 기능(기록 완료된 국내 우편)
	public int searchByNumberLocalRecord(int number) {
		for(int index=0; index<LocalMailRecordStore.getRecordLocalMaiBox().size(); index++) {
			if(LocalMailRecordStore.getRecordLocalMaiBox().get(index).getNumber() == number) {
				return index;
			} 
		}
		return -1;
	}
	
	// 우편 고유 번호로 검색하는 기능(기록 완료된 국제 우편)
	public int searchByNumberInternationalRecord(int number) {
		for(int index=0; index<InternationalMailRecordStore.getRecordInternationalMaiBox().size(); index++) {
			if(InternationalMailRecordStore.getRecordInternationalMaiBox().get(index).getNumber() == number) {
				return index;
			} 
		}
		return -1;
	}
	
	// 외부에서 접수 받은 우편함 전체 받아오기 - DB에서 컨트롤러까지 반환을 위한 메소드임
	public Stack<MailStockDto> getMailBox(){
		return externalMail.getMailStockList();
	}
	
	// 외부에서 접수한 우편 내용 추가하기 - DB에 추가하기 위한 메소드
	public void reciveMailFromCustomer(MailStockDto customerMail) {
		externalMail.recieveExternalMail(customerMail);
	}
	
	// 외부에서 접수한 우편 수정하기
	public boolean modifyMail(int number, MailStockDto modfiedfMail) { // int값은 숫자는 우편 고유번호로 고객과 우체국이 동시에 가지고 있고, MailStockDto는 우편의 형식으로 수정사항을 기입한 것 
		int idx = searchByNumber(number); // 고유값으로 접수된 우편들을 찾아본다. 만약 있으면 인덱스로 반환해줌
		if(idx>=0) { // 인덱스 값이 있다면
			externalMail.modifyExternalMail(idx, modfiedfMail); // 수정해라
			return true; // 그리고 완료된 사실을 알리기 위해 true로 반환하라
		} else {
			return false;
		}
	}

	
	// 외부에서 받은 우편 삭제
	public boolean removeExternalMail(int number) {
		if (searchByNumber(number) == -1) { // 검색을 했을 때 없다면
			return false; // false를 반환해라
		} else { // 검색을 해서 존재하면, 인덱스값을 가지고
			externalMail.deleteExternalMail(externalMail.getMailStockList().get(searchByNumber(number))); // 해당 우편함에서 찾아서 삭제하라
			return true; // 그리고 성공했음을 true로 반환하라
		}
	}
	
	////국내/국제 우편 분류 메소드
	// 우편 박스에 있는 것 분류하기
	public void popExternalMail() {
		
		// 하나 빼오기
		int len = externalMail.getMailStockList().size();
		
		// 처리한 날짜 기록을 위해 생성
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		
		for(int index=0; index<len; index++) {
			MailStockDto v = externalMail.popExternalDB();
		
			// 내용1과 2에 숫자가 있다면 국내 우편으로
			if( (Pattern.matches("^[0-9]*$", v.getStr1())) && (Pattern.matches("^[0-9]*$", v.getStr2()))){
				LocalMail lm = new LocalMail(v, (String) sdf.format(date));
				LocalMailStore.addLocallMail(lm);

				
			// 내용1과 2에 영어가 있다면 국제 우편으로
			} else if( ((Pattern.matches("^[a-zA-Z]*$", v.getStr1())) && (Pattern.matches("^[a-zA-Z]*$", v.getStr2()))) || (((Pattern.matches("^[가-힣\\s]*$", v.getStr1())) && (Pattern.matches("^[가-힣\\s]*$", v.getStr2()))) )){
				InternationalMail im = new InternationalMail(v, (String) sdf.format(date));
				InternationalMailStore.addInternationalMail(im);
				
			// 그 외는 사람의 손길이 필요하므로 정의되지 않은 우편으로
			} else {
				UndefinedStore.addUndefindMail(v);
			}
		}
	}
	
	
	//// 국내 우편 관련 메소드
	// 분류한 국내 우편 전체 출력
	public Deque<LocalMail> getLocalMail(){
		return LocalMailStore.getAllLocalMail();
	}
	
	// 뷴류한 우편들을 바탕으로 국내 우편 송신
	public boolean sendingLocalMailResult() {
		
		// 기록을 위한 날짜 생성
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		
		int lastIndex = getLocalMail().size(); // 보낼 우편이 없다면 실패를 알리는 false 반환
		if(lastIndex==0) {
			return false;
		} else {
			for(int i = 0; i < lastIndex; i++) {
				LocalMail local = LocalMailStore.sendingLocalMail(); // 기록 DB에 추가할 객체를 선언하고
				local.setEnrollDate(sdf.format(date)); // 해당 객체의 수정 날짜를 새롭게 정의하라
				LocalMailRecordStore.addRecordingLocallMail(local); // 그리고 기록 DB에 추가하라
			}
			return true;
		}
	}
	
	// 기록된 국내 우편 반환
	public ArrayList<LocalMail> getRecordingLocalMailBox() {
		return LocalMailRecordStore.getRecordLocalMaiBox();
	}
	
	//// 국제 우편 관련 메소드
	// 국제 우편 전체 출력
	public Deque<InternationalMail> getInternationalMail(){
		return InternationalMailStore.getAllInternationalMail();
	}
	
	// 국제 우편 송신 - 국내 우편 송신과 구조가 동일
	public boolean sendingInternationalMailResult() {
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		
		int lastIndex = getInternationalMail().size();
		if(lastIndex==0) {
			return false;
		} else {
			for(int i = 0; i < lastIndex; i++) {
				InternationalMail inter = InternationalMailStore.sendingLocalMail();
				inter.setEnrollDate(sdf.format(date));
				InternationalMailRecordStore.addRecordingInternationalMail(inter);
			}
			return true;
		}
		
	}
	
	// 기록된 국제 우편 반환
	public ArrayList<InternationalMail> getRecordingInternationalMailBox() {
		return InternationalMailRecordStore.getRecordInternationalMaiBox();
	}

	//// 미분류 우편 관련 메소드
	// 미분류 우편 모두 출력
	public ArrayList<MailStockDto> getUndefinedMailBox() {
		return UndefinedStore.getAllUndefindMail();
	}
	
	// 미분류 우편을 고유 우편 번호로 수정하기
	public boolean modifyUndefinedMail(int number, MailStockDto modfiedfMail) { // int값은 숫자는 우편 고유번호로 고객과 우체국이 동시에 가지고 있고, MailStockDto는 우편의 형식으로 수정사항을 기입한 것 
		int idx = searchByNumberUndefined(number); // 고유값으로 접수된 우편들을 찾아본다. 만약 있으면 인덱스로 반환해줌
		if(idx>=0) { // 인덱스 값이 있다면
			UndefinedStore.modifyUndefinedMail(idx, modfiedfMail); // 수정해라
			return true; // 그리고 완료된 사실을 알리기 위해 true로 반환하라
		} else {
			return false;
		}
	}
	
	////국내/국제 우편 분류 메소드
	// 미분류 우편 박스에 있는 것 분류하기
	public void popUndefinedMail() {
		
		// 하나 빼오기
		int len = UndefinedStore.getAllUndefindMail().size();
		
		// 처리한 날짜 기록을 위해 생성
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		
		for(int index=0; index<len; index++) {
			MailStockDto v = UndefinedStore.popUndefinedMail();
		
			// 내용1과 2에 숫자가 있다면 국내 우편으로
			if( (Pattern.matches("^[0-9]*$", v.getStr1())) && (Pattern.matches("^[0-9]*$", v.getStr2()))){
				LocalMail lm = new LocalMail(v, (String) sdf.format(date));
				LocalMailStore.addLocallMail(lm);

			
			// 내용1과 2에 영어가 있다면 국제 우편으로
			} else if( ((Pattern.matches("^[a-zA-Z]*$", v.getStr1())) && (Pattern.matches("^[a-zA-Z]*$", v.getStr2()))) || (((Pattern.matches("^[가-힣\\s]*$", v.getStr1())) && (Pattern.matches("^[가-힣\\s]*$", v.getStr2()))) )) {
				InternationalMail im = new InternationalMail(v, (String) sdf.format(date));
				InternationalMailStore.addInternationalMail(im);
				
			// 그 외는 사람의 손길이 필요하므로 정의되지 않은 우편으로
			} else {
				UndefinedStore.addUndefindMail(v);
			}
		}
	}
	
}
