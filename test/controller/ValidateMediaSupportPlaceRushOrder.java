package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateMediaSupportPlaceRushOrder {

	private PlaceRushOrderController pController;
	
	@BeforeEach
	void setUp() throws Exception {
		pController = new PlaceRushOrderController();
	}
	//gia su chi co 38 41 43 support place rush order
	@ParameterizedTest
    @CsvSource({
            "38,true",
            "41,true",
            "45,false"
    })

	
	void test(int id, boolean expected) {
		boolean isSupport = pController.checkMediaSupportRushOrder(id);
        System.out.println(id);
		assertEquals(expected, isSupport);
	}

}
