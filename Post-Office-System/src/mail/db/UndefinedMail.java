package mail.db;

import java.util.ArrayList;

import mail.dto.MailStockDto;

public class UndefinedMail{
	
	private static UndefinedMail instance = new UndefinedMail();
	private ArrayList<MailStockDto> UndefinedMailBox = new ArrayList<MailStockDto>();
	
	public static UndefinedMail getInstance(){
		return instance;
	}
	
	private UndefinedMail(){
		
	}

	public ArrayList<MailStockDto> getUndefinedMailBox() {
		return UndefinedMailBox;
	}

	public void setUndefinedMailBox(ArrayList<MailStockDto> undefinedMailBox) {
		UndefinedMailBox = undefinedMailBox;
	}

	
	// 접수받은 모든 우편 반환 (R)
	public ArrayList<MailStockDto> getAllUndefindMail(){
		return UndefinedMailBox;
	}
	
	// 분류되지 않은 우편 추가 (C)
	public void addUndefindMail(MailStockDto UndefinedMail) {
		UndefinedMailBox.add(UndefinedMail);
	}
	
	// 분류된 우편 내용 수정 (U)
	public void modifyUndefinedMail(int index, MailStockDto UndefinedMail) {
		UndefinedMailBox.get(index).setDate(UndefinedMail.getDate());
		UndefinedMailBox.get(index).setRecipient(UndefinedMail.getRecipient());
		UndefinedMailBox.get(index).setRecipientAddress(UndefinedMail.getRecipientAddress());
		UndefinedMailBox.get(index).setSender(UndefinedMail.getSender());
		UndefinedMailBox.get(index).setSenderAddress(UndefinedMail.getSenderAddress());
		UndefinedMailBox.get(index).setStr1(UndefinedMail.getStr1());
		UndefinedMailBox.get(index).setStr2(UndefinedMail.getStr2());
	}

	// 메일박스에서 외부에서 받은 우편 삭제(D)
	public MailStockDto popUndefinedMail() {
		return UndefinedMailBox.remove(0);
	}
	
}
