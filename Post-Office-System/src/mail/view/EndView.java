package mail.view;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.Stack;

import mail.dto.InternationalMail;
import mail.dto.LocalMail;
import mail.dto.MailStockDto;

public class EndView {
	
	// 메세지 출력
	public static void messageView(String string) {
		System.out.println(string);	
	}

	// 외부에서 접수된 우편 리스트들 출력
	public static void printList(Stack<MailStockDto> externalMails) {
		System.out.println();
		System.out.println("[외부에서 접수된 우편]");
		for(int i = 0; i<externalMails.size(); i++) {
			if(externalMails.get(i) != null) {
				System.out.println(externalMails.get(i));
			}
		}
	}
	
	// 미분류된 우편 리스트들 출력
	public static void printList(ArrayList<MailStockDto> Mails) {
		System.out.println();
		System.out.println("[미분류된 우편]");
		for(int i = 0; i<Mails.size(); i++) {
			if(Mails.get(i) != null) {
				System.out.println(Mails.get(i));
			}
		}
	}
	
	// 국내 우편 기록 리스트들 출력
	public static void printRecodeLocalList(ArrayList<LocalMail> Mails) {
		System.out.println();
		System.out.println("[송신된 국내 우편]");
		for(int i = 0; i<Mails.size(); i++) {
			if(Mails.get(i) != null) {
				System.out.println(Mails.get(i));
			}
		}
	}
	
	// 국제 우편 기록 리스트들 출력
	public static void printRecodeInternationalList(ArrayList<InternationalMail> Mails) {
		System.out.println();
		System.out.println("[송신된 국제 우편]");
		for(int i = 0; i<Mails.size(); i++) {
			if(Mails.get(i) != null) {
				System.out.println(Mails.get(i));
			}
		}
	}
	
	// 국내 우편 리스트들 출력
	public static void printLocalList(Deque<LocalMail> localMails) {
		System.out.println();
		System.out.println("[국내로 분류된 우편]");
		Iterator<LocalMail> it = localMails.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
		
	// 국제 우편 리스트들 출력
	public static void printInternationalList(Deque<InternationalMail> internationalMails) {
		System.out.println();
		System.out.println("[국제로 분류된 우편]");
		Iterator<InternationalMail> it = internationalMails.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	
}
