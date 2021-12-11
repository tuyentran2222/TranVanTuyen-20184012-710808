package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateRushOrderInfo {
	private PlaceRushOrderController pController;
	
	@BeforeEach
	void setUp() throws Exception {
		pController = new PlaceRushOrderController();
	}

	@ParameterizedTest
    @CsvSource({
            "Test instruction,true",
            "123s,false",
            "#123a,false"
    })
	
	void test(String info, boolean expected) {
        boolean isValid = pController.validateRushOrderInfo(info);
        assertEquals(isValid, expected);
    }
}
