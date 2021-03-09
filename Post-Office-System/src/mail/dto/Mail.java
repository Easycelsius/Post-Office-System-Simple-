package mail.dto;

public class Mail {
	private String sender; // 보낸 이
	private String senderAddress; // 보낸 이 주소
	private String recipient; // 받는 이 
	private String recipientAddress; // 받는 이 주소
	
	public Mail() {}
	public Mail(String sender, String senderAddress, String recipient, String recipientAddress) {
		this.sender = sender;
		this.recipient = recipient;
		this.senderAddress = senderAddress;
		this.recipientAddress = recipientAddress;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getRecipientAddress() {
		return recipientAddress;
	}
	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" / 보낸 이 : ");
		builder.append(sender);
		builder.append(" / 보낸 이 주소: ");
		builder.append(senderAddress);
		builder.append(" / 받는 이 : ");
		builder.append(recipient);
		builder.append(" / 받는 이 주소 : ");
		builder.append(recipientAddress);
		return builder.toString();
	}
	
	
}
