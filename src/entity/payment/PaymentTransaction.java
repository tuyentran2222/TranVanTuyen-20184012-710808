package entity.payment;

public class PaymentTransaction {
	private String errorCode;
	private PaymentCard card;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;
	
	/**
	 * constructor
	 * @param errorCode
	 * @param card
	 * @param transactionId
	 * @param transactionContent
	 * @param amount
	 * @param createdAt
	 * @author TuyenTV_20184012
	 */
	public PaymentTransaction(String errorCode, PaymentCard card, String transactionId, String transactionContent,
			int amount, String createdAt) {
		super();
		this.errorCode = errorCode;
		this.card = card;
		this.transactionId = transactionId;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
