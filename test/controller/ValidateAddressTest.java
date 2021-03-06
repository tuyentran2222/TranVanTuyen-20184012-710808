package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * test address input
 * @author TuyenTV_20184012
 *
 */
class ValidateAddressTest {

	private PlaceOrderController placeOrderController;
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}
	@ParameterizedTest
	@CsvSource({
		"Dan Phuong,true",
		"Dong Da, true",
		"123,false",
		"adc123,false",
		"Ha Noi,true"
	})
	
	void test(String address, boolean expected) {
		boolean isValid = placeOrderController.validateAddress(address);
		System.out.println(address);
		assertEquals(expected, isValid);
	}

}
