package mail.dto;

public class LocalMail extends MailStockDto{
	
	private String senderAddressCode; // 보낸이의 국내 우편주소
	private String recipientAddressCode; // 받는이의 국내 우편주소
	private String enrollDate; // 수정된 사항이 발생할 때마다 시간을 기록하는 변수
	
	public LocalMail() {}
	public LocalMail(int number, String date, String sender, String senderAddress, String recipient, String recipientAddress, String senderAddressCode, String recipientAddressCode, String enrollDate) {
		super(number, date, sender, senderAddress, recipient, recipientAddress, senderAddressCode, recipientAddressCode);
		this.senderAddressCode = senderAddressCode;
		this.recipientAddressCode = recipientAddressCode;
		this.setEnrollDate(enrollDate);
	}
	public LocalMail(MailStockDto externalMail, String enrollDate) {
		super(externalMail.getNumber(), externalMail.getDate(), externalMail.getSender(), externalMail.getSenderAddress(), externalMail.getRecipient(), externalMail.getRecipientAddress(), externalMail.getStr1(), externalMail.getStr2());
		this.senderAddressCode = externalMail.getStr1();
		this.recipientAddressCode = externalMail.getStr2();
		this.setEnrollDate(enrollDate);
	}
	
	public String getSenderAddressCode() {
		return senderAddressCode;
	}
	public void setSenderAddressCode(String senderAddressCode) {
		this.senderAddressCode = senderAddressCode;
	}
	public String getRecipientAddressCode() {
		return recipientAddressCode;
	}
	public void setRecipientAddressCode(String recipientAddressCode) {
		this.recipientAddressCode = recipientAddressCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("우편고유번호: ");
		builder.append(getNumber());		
		builder.append(" / 외부로 부터 들어온 날짜: ");
		builder.append(getDate());
		builder.append(" / 보낸 이 : ");
		builder.append(getSender());
		builder.append(" / 보낸 이 주소: ");
		builder.append(getSenderAddress());
		builder.append(" / 받는 이 : ");
		builder.append(getRecipient());
		builder.append(" / 받는 이 주소 : ");
		builder.append(getRecipientAddress());
		builder.append(" / 보낸 이 우편 코드 : ");
		builder.append(senderAddressCode);		
		builder.append(" / 받는 이 우편 코드 : ");
		builder.append(recipientAddressCode);		
		builder.append(" / 마지막으로 수행된 날짜: ");
		builder.append(enrollDate);
		return builder.toString();
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	
}
