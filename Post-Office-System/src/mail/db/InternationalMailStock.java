/* InternationalMailStock
 *  - MailStock에서 분류한 국제 우편을 이 곳에 저장
 * 
 */

package mail.db;

import java.util.Deque;
import java.util.LinkedList;

import mail.dto.InternationalMail;

public class InternationalMailStock {
	
	private static InternationalMailStock instance = new InternationalMailStock();
	Deque<InternationalMail> internationalMailStockBox = new LinkedList<>();
	
	public static InternationalMailStock getInstance(){
		return instance;
	}
	
	private InternationalMailStock() {
		
	}

	public Deque<InternationalMail> getInternationalMailStockBox() {
		return internationalMailStockBox;
	}

	public void setInternationalMailStockBox(Deque<InternationalMail> internationalMailStockBox) {
		this.internationalMailStockBox = internationalMailStockBox;
	}

	// 분류된 국제 우편 추가
	public void addInternationalMail(InternationalMail definedMail) {
		internationalMailStockBox.add(definedMail);
	}
	
	// 분류된 국제 우편 전체 반환
	public Deque<InternationalMail> getAllInternationalMail() {
		return internationalMailStockBox;
	}
	
	// 분류된 국제 우편 송신
	public InternationalMail sendingLocalMail() {
		return internationalMailStockBox.poll();
	}
	
	
}
