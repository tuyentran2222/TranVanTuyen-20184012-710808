package views.screen.shipping;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.PlaceOrderController;
import controller.PlaceRushOrderController;
import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.media.Media;
import entity.order.Order;
import entity.order.OrderMedia;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;
import views.screen.popup.PopupScreen;

public class ShippingScreenHandler extends BaseScreenHandler implements Initializable {

	@FXML
	private Label screenTitle;

	@FXML
	private TextField name;

	@FXML
	private TextField phone;

	@FXML
	private TextField address;

	@FXML
	private TextField instructions;

	@FXML
	private ComboBox<String> province;
	
	@FXML
	private CheckBox checkboxPlaceRushOrder;

	private Order order;

	 /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(ShippingScreenHandler.class.getName());
    
	public ShippingScreenHandler(Stage stage, String screenPath, Order order) throws IOException {
		super(stage, screenPath);
		this.order = order;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
		name.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                content.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
		this.province.getItems().addAll(Configs.PROVINCES);
	}

	@FXML
	void submitDeliveryInfo(MouseEvent event) throws IOException, InterruptedException, SQLException {

		// add info to messages
		HashMap messages = new HashMap<>();
		messages.put("name", name.getText());
		messages.put("phone", phone.getText());
		messages.put("address", address.getText());
		messages.put("instructions", instructions.getText());
		messages.put("province", province.getValue());
		LOGGER.info("1230");
		try {
			// process and validate delivery info
			getBController().processDeliveryInfo(messages);
		} catch (InvalidDeliveryInfoException e) {
			throw new InvalidDeliveryInfoException(e.getMessage());
		}
		
		LOGGER.info("a1230");
		Boolean checkLocationSupportRushOrder = true;
		
		Boolean checkAllMediaNotSRO = false;
		
		if (checkboxPlaceRushOrder.isSelected()) {
			String location = province.getValue();
			LOGGER.info(location);
			PlaceRushOrderController placeRushOrderController = new PlaceRushOrderController();
			List<OrderMedia> lists = this.order.getlstOrderMedia();
			checkLocationSupportRushOrder = placeRushOrderController.checkLocationSupportRushOrder(location);
			//dem so san pham khong ho tro giao hang nhanh
			int count = 0;
			
			if (checkLocationSupportRushOrder) {
				for (OrderMedia m:lists) {
					int mediaId = m.getMedia().getId();
					LOGGER.info(mediaId + " ");
					if (!placeRushOrderController.checkSupportRushOrder(location, mediaId))
					count++;
				}
				
				// kiem tra so san pham khong ho tro giao hang 
				if (count == lists.size()) {
					PopupScreen.error("All items are not supported place rush order");
					checkAllMediaNotSRO = true;
				}
			}
			else {
				PopupScreen.error("Location delivery is not supported place rush order");
			}
			
		}
		
		if (checkLocationSupportRushOrder && !checkAllMediaNotSRO) {
			// calculate shipping fees
			int shippingFees = getBController().calculateShippingFee(order);
			order.setShippingFees(shippingFees);
			order.setDeliveryInfo(messages);
			
			// create invoice screen
			Invoice invoice = getBController().createInvoice(order);
			BaseScreenHandler InvoiceScreenHandler = new InvoiceScreenHandler(this.stage, Configs.INVOICE_SCREEN_PATH, invoice);
			InvoiceScreenHandler.setPreviousScreen(this);
			InvoiceScreenHandler.setHomeScreenHandler(homeScreenHandler);
			InvoiceScreenHandler.setScreenTitle("Invoice Screen");
			InvoiceScreenHandler.setBController(getBController());
			InvoiceScreenHandler.show();
		}
	
	}

	public PlaceOrderController getBController(){
		return (PlaceOrderController) super.getBController();
	}

	public void notifyError(){
		// TODO: implement later on if we need
	}

}
