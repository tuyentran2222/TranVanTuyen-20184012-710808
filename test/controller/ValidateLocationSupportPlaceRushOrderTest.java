package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * test location is supported place rush order.
 * @author TuyenTV_20184012
 *
 */
class ValidateLocationSupportPlaceRushOrderTest {

	private PlaceRushOrderController pController;
	
	@BeforeEach
	void setUp() throws Exception {
		pController = new PlaceRushOrderController();
	}
	
	@ParameterizedTest
    @CsvSource({
            "Hà Nội,true",
            "Nam Định,false",
            "H63a,false"
    })

	void test(String province, boolean expected) {
        boolean isValid = pController.checkLocationSupportRushOrder(province);
        System.out.println(province);
		assertEquals(expected, isValid);

    }

}
