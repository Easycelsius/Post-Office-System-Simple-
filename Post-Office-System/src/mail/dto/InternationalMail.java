package mail.dto;

public class InternationalMail extends MailStockDto{
	private String senderCountry; // 보낸이의 국가
	private String recipientCountry; // 받는이의 국가
	private String enrollDate; // 수정된 사항이 발생할 때마다 시간을 기록하는 변수
	
	public InternationalMail() {}
	public InternationalMail(int number, String date, String sender, String senderAddress, String recipient, String recipientAddress, String senderCountry, String recipientCountry, String enrollDate) {
		super(number, date, sender, senderAddress, recipient, recipientAddress, senderCountry, recipientCountry);
		this.senderCountry = senderCountry;
		this.recipientCountry = recipientCountry;
		this.setEnrollDate(enrollDate);
	}
	public InternationalMail(MailStockDto externalMail, String enrollDate) {
		super(externalMail.getNumber(), externalMail.getDate(), externalMail.getSender(), externalMail.getSenderAddress(), externalMail.getRecipient(), externalMail.getRecipientAddress(), externalMail.getStr1(), externalMail.getStr2());
		this.senderCountry = externalMail.getStr1();
		this.recipientCountry = externalMail.getStr2();
		this.setEnrollDate(enrollDate);
	}
	public String getSenderCountry() {
		return senderCountry;
	}
	public void setSenderCountry(String senderCountry) {
		this.senderCountry = senderCountry;
	}
	public String getRecipientCountry() {
		return recipientCountry;
	}
	public void setRecipientCountry(String recipientCountry) {
		this.recipientCountry = recipientCountry;
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
		builder.append(" / 보낸 이의 국가: ");
		builder.append(senderCountry);		
		builder.append(" / 받는 이의 국가: ");
		builder.append(recipientCountry);
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
