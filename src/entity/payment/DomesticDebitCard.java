package entity.payment;

public class DomesticDebitCard extends PaymentCard{
	private String issuingBank;
	private String cardNumber;
	private String validDate;
	private String cardHolderName;
	
	//Default constructor
	public DomesticDebitCard() {
		
	}
	
	public DomesticDebitCard(String issuingBank, String cardNumber, String validDate, String cardHolderName) {
		super();
		this.issuingBank = issuingBank;
		this.cardNumber = cardNumber;
		this.validDate = validDate;
		this.cardHolderName = cardHolderName;
	}

	//getting and setting method
	public String getIssuingBank() {
		return issuingBank;
	}

	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	
}
