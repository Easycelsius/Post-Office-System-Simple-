package mail.db;

import java.util.ArrayList;

import mail.dto.InternationalMail;

public class RecordInternationalMail {
	private static RecordInternationalMail instance = new RecordInternationalMail();
	ArrayList<InternationalMail> RecordInternationalMailBox = new ArrayList<>();
	
	public static RecordInternationalMail getInstance() {
		return instance;
	}
	
	private RecordInternationalMail() {
		
	}
	
	// 송신된 국제 우편 전체 반환
	public ArrayList<InternationalMail> getRecordInternationalMaiBox() {
		return RecordInternationalMailBox;
	}
	
	
	// 송신된 국제 우편 setter
	public void setRecordInternationalMaiBox(ArrayList<InternationalMail> recordLocalMaiBox) {
		RecordInternationalMailBox = recordLocalMaiBox;
	}
	
	// 송신된 국제 우편 기록 추가
	public void addRecordingInternationalMail(InternationalMail definedMail) {
		RecordInternationalMailBox.add(definedMail);
	}
}
