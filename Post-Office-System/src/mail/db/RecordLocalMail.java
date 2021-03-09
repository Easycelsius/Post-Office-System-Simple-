package mail.db;


import java.util.ArrayList;

import mail.dto.LocalMail;

public class RecordLocalMail {
	private static RecordLocalMail instance = new RecordLocalMail();
	ArrayList<LocalMail> RecordLocalMailBox = new ArrayList<>();
	
	public static RecordLocalMail getInstance() {
		return instance;
	}
	
	private RecordLocalMail() {
		
	}
	
	// 송신된 국내 우편 전체 반환
	public ArrayList<LocalMail> getRecordLocalMaiBox() {
		return RecordLocalMailBox;
	}
	
	
	// 송신된 국내 우편 setter
	public void setRecordLocalMaiBox(ArrayList<LocalMail> recordLocalMaiBox) {
		RecordLocalMailBox = recordLocalMaiBox;
	}
	
	// 송신된 국내 우편 기록 추가
	public void addRecordingLocallMail(LocalMail definedMail) {
		RecordLocalMailBox.add(definedMail);
	}
	
	
		
	
}
