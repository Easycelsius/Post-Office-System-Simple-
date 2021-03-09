package mail.dto;

public class MailStockDto extends Mail{
	private int number; // 접수번호
	private String date; // 접수날짜기록
	private String str1; // 사용자가 기입할 내용 1
	private String str2; // 사용자가 기입할 내용 2
	
	public MailStockDto() {}
	public MailStockDto(int number, String date, String sender, String senderAddress, String recipient, String recipientAddress, String str1, String str2) {
		super(sender, senderAddress, recipient, recipientAddress);
		this.number = number;
		this.date = date;
		this.str1 = str1;
		this.str2 = str2;
	}
	public int getNumber() {
		return number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("우편고유번호: ");
		builder.append(number);		
		builder.append(" / 외부로 부터 들어온 날짜: ");
		builder.append(date);
		builder.append(super.toString());
		builder.append(" / 내용1: ");
		builder.append(str1);	
		builder.append(" / 내용2: ");
		builder.append(str2);	
		return builder.toString();
	}
}
