package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import entity.cart.Cart;
import entity.cart.CartMedia;
import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;
import views.screen.popup.PopupScreen;

/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */
public class PlaceOrderController extends BaseController{

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the avalibility of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException{
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
    }
    
    /**
     * The method validates the info
     * @param info
     * @return value validate info
     * @throws InterruptedException
     * @throws IOException
     */
    public boolean validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
    	String address = info.get("address");
    	String name = info.get("name");
    	String phone = info.get("phone");
    	boolean check = validateAddress(address) && validateName(name) && validatePhoneNumber(phone);
    	return check;
    }
    
    /**
     * validate phone number
     * @param phoneNumber phone number of user
     * @return result validate phone number
     */
    public boolean validatePhoneNumber(String phoneNumber) {
    	// Tran Van Tuyen 20184012
    	// check phone number is null
    	if (phoneNumber == null) return false;
    	//check phoneNumber has 10 digits
    	if (phoneNumber.length() != 10) return false;
    	//check the phoneNumber starts with 0
    	if  (!phoneNumber.startsWith("0")) return false;
    	//check phone number contains only number
    	try {
    		Integer.parseInt(phoneNumber);
    	}
    	catch (NumberFormatException e) {
    		return false;
    	}
    	return true;
    }
    
    /**
     * validate name of customer
     * @param name name of customer
     * @return result validate
     */
    public boolean validateName(String name) {
    	//check emty name
    	if (name == null || name.length() == 0) return false;
    	
    	//check name only have letter or space
    	for (char c : name.toCharArray()) {
    		if (c == ' ' || Character.isLetter(c)) {
    			continue;
    		}
    		else return false;
    	}
    	return true;
    }
    
    /**
     * validate address of receive product
     * @param address address of customer
     * @return 
     */
    public boolean validateAddress(String address) {
    	//check emty name
    	if (address == null || address.length() == 0) return false;
    	
    	//check name only have letter or space or ,
    	for (char c : address.toCharArray()) {
    		if (c ==' ' || Character.isLetter(c) || c == ',') {
    			continue;
    		}
    		else return false;
    	}
    	return true;
    }
    

    /**
     * This method calculates the shipping fees of order
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
