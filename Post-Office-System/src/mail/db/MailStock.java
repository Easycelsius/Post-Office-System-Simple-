/* MailStock 설명 
 *  - 외부에서 들어온 우편에 관한 DB
 *  - CRUD 기능으로 접수받은 우편의 유입, 수정, 취소, 검색을 구현
 */

package mail.db;

import java.util.Stack;

import mail.dto.MailStockDto;

public class MailStock {
	
	private static MailStock instance = new MailStock();
	private Stack<MailStockDto> MailBox = new Stack<MailStockDto>();
//	private ArrayList<MailStockDto> MailBox = new ArrayList<MailStockDto>();

	private MailStock() {
		MailBox.push(new MailStockDto(1, "210201101003", "testSender1", "testSenderAddress1", "testRecipient1", "testRecipientAddress1", "Korea", "France"));
		MailBox.push(new MailStockDto(2, "210201101101", "testSender2", "testSenderAddress2", "testRecipient2", "testRecipientAddress2", "한국", "영국"));
		MailBox.push(new MailStockDto(3, "210201101421", "testSender3", "testSenderAddress3", "testRecipient3", "testRecipientAddress3", "06111", "23214"));
		MailBox.push(new MailStockDto(4, "210201101530", "testSender4", "testSenderAddress4", "testRecipient4", "testRecipientAddress4", "???", "22244"));
	}
	
	public static MailStock getInstance() {
		return instance;
	}
	
	// 접수받은 모든 우편 반환 (C)
	public Stack<MailStockDto> getMailStockList(){
		return MailBox;
	}
	
	// 메일박스에 외부에서 받은 우편 추가 (R)
	public void recieveExternalMail(MailStockDto ExternalMail) {
		this.MailBox.push(ExternalMail);
	}
	
	// 접수한 우편 내용 수정 (U)
	public void modifyExternalMail(int index, MailStockDto ExternalMail) {
		MailBox.get(index).setDate(ExternalMail.getDate());
		MailBox.get(index).setRecipient(ExternalMail.getRecipient());
		MailBox.get(index).setRecipientAddress(ExternalMail.getRecipientAddress());
		MailBox.get(index).setSender(ExternalMail.getSender());
		MailBox.get(index).setSenderAddress(ExternalMail.getSenderAddress());
		MailBox.get(index).setStr1(ExternalMail.getStr1());
		MailBox.get(index).setStr2(ExternalMail.getStr2());
	}
	
	// 메일박스에서 외부에서 받은 우편 삭제(D)
	public void deleteExternalMail(MailStockDto ExternalMail) {
		this.MailBox.remove(ExternalMail);
	}
	
	// 메일박스 pop 기능
	public MailStockDto popExternalDB() {
		return MailBox.pop();
	}
	
}


