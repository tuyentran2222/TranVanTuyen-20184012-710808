package entity.order;
import java.time.LocalDateTime;    
public class PlaceRushOrder {
	public Order order;
	private String instruction;
	private String information;
	private LocalDateTime receiveDate;
	
	public PlaceRushOrder(Order order, String instruction, String information, LocalDateTime receiveDate) {
		super();
		this.order = order;
		this.instruction = instruction;
		this.information = information;
		this.receiveDate = receiveDate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public LocalDateTime getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(LocalDateTime receiveDate) {
		this.receiveDate = receiveDate;
	}

}
