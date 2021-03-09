package mail.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.Stack;

import mail.dto.InternationalMail;
import mail.dto.LocalMail;
import mail.dto.MailStockDto;
import mail.model.MailProjectModel;
import mail.view.EndView;
import mail.view.FailView;

public class MailProjectController {
	
	private static MailProjectController instance = new MailProjectController();
	private MailProjectModel service = MailProjectModel.getInstance();
	
	private MailProjectController() {}
	
	public static MailProjectController getInstance() {
		return instance;
	}
	
	// 외부 우편함 모두 출력
	public void allExternalMails() {
		Stack<MailStockDto> externalMails = service.getMailBox();
		if (externalMails.size()==0) {
			EndView.messageView("외부에서 접수된 우편 항목이 비어있습니다");
		} else {
			EndView.printList(externalMails);
		}
	}
	
	// 외부 우편 접수
	public void receiptFromExternalMail(String sender, String senderAddress, String recipient, String recipientAddress, String str1, String str2) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		MailStockDto externalMail = new MailStockDto(service.makeNumber(), sdf.format(date), sender, senderAddress, recipient, recipientAddress, str1, str2);
		service.reciveMailFromCustomer(externalMail);
		EndView.messageView("이 우편에 배정된 고유번호는 " + (service.makeNumber()-1) +"번 입니다 \n우편 내용 수정 및 처리 상황 조회시 사용될 수 있으니 기억해두시길 바랍니다");
	}
	
	// 접수한 우편 내용 수정하기
	public void modifyExternalMail(int number, String sender, String senderAddress, String recipient, String recipientAddress, String str1, String str2) {	
		
		// 수정한 날짜를 기록하기 위해 생성
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		
		// 객체를 새롭게 생성해서 수정사항을 위한 메소드의 매개변수와 매칭
		MailStockDto externalMail = new MailStockDto(number, sdf.format(date), sender, senderAddress, recipient, recipientAddress, str1, str2);
		if(service.modifyMail(number, externalMail) == false) {
			FailView.failMessageView("번호를 다시 확인해 주세요");
		} else {
			EndView.messageView("정상적으로 수정하였습니다");
		};
	}
	
	// 접수한 우편 삭제하기
	public void removeExternalMail(int number) {
		if(service.removeExternalMail(number)) {
			EndView.messageView("정상적으로 제거되었습니다");
		} else {
			FailView.failMessageView("존재하지 않는 번호입니다");
		}
	}

	
	// 접수한 우편을 국내/국외로 분류하여 DB에 보내기
	public void classifyExternalMail() {
		service.popExternalMail();
		if(service.getMailBox().size()==0) {
			EndView.messageView("외부에서 접수된 우편 분류를 위해 기능을 실행합니다");
		}
	}
	
	// 국내 분류 우편 모두 출력
	public void allLocalMails() {
		Deque<LocalMail> Local = service.getLocalMail();
		if (Local.size()==0) {
			EndView.messageView("국내 우편 발송과 관련하여 대기 중인 우편이 없습니다");
		} else {
			EndView.printLocalList(Local);
		}
	}
	
	// 국제 분류 우편 모두 출력
	public void allInternationalMails() {
		Deque<InternationalMail> Inter = service.getInternationalMail();
		if (Inter.size()==0) {
			EndView.messageView("국제 우편 발송과 관련하여 대기 중인 우편이 없습니다");
		} else {
			EndView.printInternationalList(Inter);
		}
	}
	
	// 미분류 우편 모두 출력
	public void allUndefinedMails() {
		ArrayList<MailStockDto> undefinedMails = service.getUndefinedMailBox();
		if (undefinedMails.size()==0) {
			EndView.messageView("미분류 우편 항목이 없습니다");
		} else {
			EndView.printList(undefinedMails);
		}
	}

	// 국내/국제 우편 보내기
	public void sendingLocalMail() {
		if(service.sendingLocalMailResult()) {
			EndView.messageView("국내 우편이 정상적으로 송신되었습니다");
		} else {
			EndView.messageView("송신할 우편이 없습니다");
		}
	}
	
	public void sendingInternationalMail() {		
		if(service.sendingInternationalMailResult()) {
			EndView.messageView("국제 우편이 정상적으로 송신되었습니다");
		} else {
			EndView.messageView("송신할 우편이 없습니다");
		}
	}
	
	// 송신한 국내 우편 출력
	public void recordLocal() {
		ArrayList<LocalMail> local = service.getRecordingLocalMailBox();
		if (local.size()==0) {
			EndView.messageView("기록된 국내 우편이 없습니다");
		} else {
			EndView.printRecodeLocalList(local);
		}
	}
	
	// 송신한 해외 우편 출력
	public void recordInternational() {
		ArrayList<InternationalMail> inter = service.getRecordingInternationalMailBox();
		if (inter.size()==0) {
			EndView.messageView("기록된 국제 우편이 없습니다");
		} else {
			EndView.printRecodeInternationalList(inter);
		}
	}

	
	// 전체 우편함을 검색하여 처리 중인 위치 출력
	// 각 부분을 검색해서 인덱스가 반환되었다면, 그 곳에 존재한다는 의미임으로 다음과 같은 문구를 출력하도록 함
	public void searchMyMail(int number) {
		if(service.searchByNumber(number)>=0) {
			EndView.messageView("해당 우편(" + number + ")은 분류를 위해 창고에 대기 중 입니다");
		} else if(service.searchByNumberLocal(number)>=0) {
			EndView.messageView("해당 우편("  + number + ")은 국제 우편 전송 대기 중에 있습니다");
		} else if(service.searchByNumberInternational(number)>=0) {
			EndView.messageView("해당 우편("  + number + ")은 국제 우편 전송 대기 중에 있습니다");
		} else if(service.searchByNumberUndefined(number)>=0) {
			EndView.messageView("해당 우편("  + number + ")은 재분류를 위한 작업 중에 있습니다");
		} else if(service.searchByNumberLocalRecord(number)>=0) {
			EndView.messageView("해당 우편("  + number + ")은 국내로 송신하였습니다");
		} else if(service.searchByNumberInternational(number)>=0) {
			EndView.messageView("해당 우편("  + number + ")은 국외로 송신하였습니다");
		} else {
			FailView.failMessageView("기입한 우편 고유 번호로는 DB내에서 찾을 수 없었습니다");
		}	
	}
	
	// 미분류 우편 수정
	public void modifyUndefinedMail(int number, String sender, String senderAddress, String recipient, String recipientAddress, String str1, String str2) {	
		
		// 수정한 날짜를 기록하기 위해 생성
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		
		// 객체를 새롭게 생성해서 수정사항을 위한 메소드의 매개변수와 매칭
		MailStockDto modfiedfMail = new MailStockDto(number, sdf.format(date), sender, senderAddress, recipient, recipientAddress, str1, str2);
		if(service.modifyUndefinedMail(number, modfiedfMail) == false) {
			FailView.failMessageView("번호를 다시 확인해 주세요");
		} else {
			EndView.messageView("정상적으로 수정하였습니다");
		};
	}
	
	// 우편 수정 기능 통합
	public void modifyMail(int number, String sender, String senderAddress, String recipient, String recipientAddress, String str1, String str2) {
		if(service.searchByNumber(number)>=0) {
			EndView.messageView("해당 우편(" + number + ")은 분류를 위해 창고에 대기 중이며 수정을 진행합니다");
			modifyExternalMail(number, sender, senderAddress, recipient, recipientAddress, str1, str2);
		} else if(service.searchByNumberUndefined(number)>=0) {
			EndView.messageView("해당 우편("  + number + ")은 재분류를 위한 작업 중에 있으며 수정을 진행합니다");
			modifyUndefinedMail(number, sender, senderAddress, recipient, recipientAddress, str1, str2);
		} else {
			FailView.failMessageView("번호를 다시 확인해 주세요");
		}
	}
	
	// 미분류한 우편을 국내/국외로 분류하여 DB에 보내기
	public void classifyUndefinedMail() {
		service.popUndefinedMail();
		if(service.getUndefinedMailBox().size()==0) {
			EndView.messageView("미분류 된 우편 분류를 위해 기능을 실행합니다");
		} else {
			EndView.messageView("미분류 우편함에 아직 분류되지 않은 우편이 남아있습니다");
		}
	}
	
}
