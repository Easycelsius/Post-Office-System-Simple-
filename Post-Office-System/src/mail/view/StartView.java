package mail.view;

import mail.controller.MailProjectController;
import java.util.Scanner;

public class StartView {

	public static String scan(String sentence) {
		Scanner sc = new Scanner(System.in);
		System.out.println(sentence);
		return sc.next();
	}
	
	public static int numScanner(String str) {	
		try {
			return Integer.parseInt(scan(str));	
		} catch (NumberFormatException e){
			System.out.println("유효한 번호를 입력해주세요");
		}
		return 0;
	}
	
	public static void main(String[] args) {
		MailProjectController controller = MailProjectController.getInstance();
		
		boolean stopSignal1 = true;
		while(stopSignal1) {
			
			System.out.println("\n===== 우편시스템에 접속하신 것을 환영합니다 =====");
			System.out.println("-------------------------------------");
			System.out.println("01. 새롭게 우편 접수하기");
			System.out.println("02. 접수한 우편 리스트 전체 조회");
			System.out.println("03. 접수한 우편 수정하기");
			System.out.println("04. 접수한 우편 삭제하기");
			System.out.println("-------------------------------------");

			System.out.println("05. 접수받은 우편 분류하기");
			System.out.println("-------------------------------------");
			
			System.out.println("06. 분류된 국내 우편 전체 조회하기");
			System.out.println("07. 분류된 국제 우편 전체 조회하기");
			System.out.println("08. 미분류된 우편 전체 조회하기");
			System.out.println("-------------------------------------");
			
			System.out.println("09. 분류된 국내 우편 송신하기");
			System.out.println("10. 분류된 국제 우편 송신하기");
			System.out.println("-------------------------------------");
			
			System.out.println("11. 미분류된 우편 수정하기");
			System.out.println("12. 미분류된 우편 재분류하기");
			System.out.println("-------------------------------------");
			
			System.out.println("13. 송신한 국내우편 기록 조회하기");
			System.out.println("14. 송신한 국제우편 기록 조회하기");
			System.out.println("-------------------------------------");
			
			System.out.println("15. 우편 처리 상황 조회하기");
			System.out.println("-------------------------------------");
			
			System.out.println("16. DB 한번에 조회하기");
			System.out.println("-------------------------------------");
			
			System.out.println("17. 종료");
			System.out.println("-------------------------------------");
			
			int select = numScanner("원하시는 서비스의 번호를 입력해주세요");
			
			if(select == 1) {
				controller.receiptFromExternalMail(scan("보내는 분 성함을 입력해주세요"), scan("보내는 분 주소를 입럭해주세요"), scan("받으시는 분 성함을 입력해주세요"), scan("받으시는분 주소를 입력해주세요"), scan("국내로 보내시는 거면 보내는 이의 우편코드, 국외로 보내시는 거면 보내는 이의 국가를 입력해주세요(영문 기재 요망)"), scan("국내로 보내시는 거면 받는 이의 우편코드, 국외로 보내시는 거면 받는 이의 국가를 입력해주세요(영문 기재 요망)"));
			} else if(select == 2) {
				controller.allExternalMails();
			} else if(select == 3) {
				controller.modifyMail(numScanner("접수할 때 생성된 우편 고유 번호를 입력해주세요"), scan("보내는 분 성함을 입력해주세요"), scan("보내는 분 주소를 입럭해주세요"), scan("받으시는 분 성함을 입력해주세요"), scan("받으시는분 주소를 입력해주세요"), scan("국내로 보내시는 거면 보내는 이의 우편코드, 국외로 보내시는 거면 보내는 이의 국가를 입력해주세요(영문 기재 요망)"), scan("국내로 보내시는 거면 받는 이의 우편코드, 국외로 보내시는 거면 받는 이의 국가를 입력해주세요(영문 기재 요망)"));
			} else if(select == 4) {
				controller.removeExternalMail(numScanner("접수할 때 생성된 우편 고유 번호를 입력해주세요"));
			} else if(select == 5) {
				controller.classifyExternalMail();
			} else if(select == 6) {
				controller.allLocalMails();
			} else if(select == 7) {
				controller.allInternationalMails();
			} else if(select == 8) {
				controller.allUndefinedMails();
			} else if(select == 9) {
				controller.sendingLocalMail();
			} else if(select == 10) {
				controller.sendingInternationalMail();
			} else if(select == 12) {
				controller.classifyUndefinedMail();
			} else if(select == 11) {
				controller.modifyUndefinedMail(numScanner("접수할 때 생성된 우편 고유 번호를 입력해주세요"), scan("보내는 분 성함을 입력해주세요"), scan("보내는 분 주소를 입럭해주세요"), scan("받으시는 분 성함을 입력해주세요"), scan("받으시는분 주소를 입력해주세요"), scan("국내로 보내시는 거면 보내는 이의 우편코드, 국외로 보내시는 거면 보내는 이의 국가를 입력해주세요(영문 기재 요망)"), scan("국내로 보내시는 거면 받는 이의 우편코드, 국외로 보내시는 거면 받는 이의 국가를 입력해주세요(영문 기재 요망)"));
			} else if(select == 13) {
				controller.recordLocal();
			} else if(select == 14) {
				controller.recordInternational();
			} else if(select == 15) {
				controller.searchMyMail(numScanner("접수할 때 생성된 우편 고유 번호를 입력해주세요"));
			} else if(select == 16) {
				controller.allExternalMails();
				controller.allLocalMails();
				controller.allInternationalMails();	
				controller.allUndefinedMails();
				controller.recordLocal();
				controller.recordInternational();
			} else if(select == 17) {
				stopSignal1 = false;
				System.out.println("정상적으로 종료되었습니다");
				System.out.println("항상 저희 서비스를 이용해 주셔서 감사합니다");
			} else {
				System.out.println("유효한 번호를 다시 입력해주세요");
			}

		}

	}

}
