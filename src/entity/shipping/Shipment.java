package entity.shipping;

import java.time.LocalDateTime;

public class Shipment {
    private String instruction;
    private LocalDateTime receiveTime;
    private String information;
    
    public Shipment(String instruction, LocalDateTime receiveTime, String information) {
		this.instruction = instruction;
		this.receiveTime = receiveTime;
		this.information = information;
	}

    public Shipment() {
    	
    }
    
	public void validateDeliveryInfo(){
        // TODO: implement later on
    }

    public Shipment createNewShipment(){
        // TODO: implement later on
        return new Shipment();
    }

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public LocalDateTime getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(LocalDateTime receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
}
