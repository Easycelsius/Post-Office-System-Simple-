/* LocalMailStock
 *  - MailStock에서 분류한 국제 우편을 이 곳에 저장
 * 
 */

package mail.db;

import java.util.Deque;
import java.util.LinkedList;

import mail.dto.LocalMail;

public class LocalMailStock {
	
	private static LocalMailStock instance = new LocalMailStock();
	Deque<LocalMail> LocalMailStockBox = new LinkedList<>();
	
	public static LocalMailStock getInstance() {
		return instance;
	}
	
	private LocalMailStock() {
		
	}
	

	public Deque<LocalMail> getLocalMailStock() {
		return LocalMailStockBox;
	}

	public void setLocalMailStock(Deque<LocalMail> localMailStock) {
		LocalMailStockBox = localMailStock;
	}
	
	// 분류된 국내 우편 추가
	public void addLocallMail(LocalMail definedMail) {
		LocalMailStockBox.add(definedMail);
	}
	
	// 분류된 국내 우편 전체 반환
	public Deque<LocalMail> getAllLocalMail() {
		return LocalMailStockBox;
	}
	
	// 분류된 국내 우편 송신
	public LocalMail sendingLocalMail() {
		return LocalMailStockBox.poll();
	}

}
