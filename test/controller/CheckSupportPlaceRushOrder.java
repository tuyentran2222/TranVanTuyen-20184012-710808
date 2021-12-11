package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * test support place rush order
 * @author TuyenTV_20184012
 *
 */
class CheckSupportPlaceRushOrder {

	private PlaceRushOrderController pController;
	
	@BeforeEach
	void setUp() throws Exception {
		pController = new PlaceRushOrderController();
	}

	@ParameterizedTest
    @CsvSource({
            "Hà Nội,38,true",
            "Nam Định,45,false",
            "Hà Nội,45,false"
    })
	
	void test(String province, int id, boolean expected) {
        boolean isValid = pController.checkSupportRushOrder(province, id);
        System.out.print(province);
        assertEquals(isValid, expected);
    }

}
