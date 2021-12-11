package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * test receive time
 * @author TuyenTV_20184012
 *
 */
class ValidateReceiveTime{

	private PlaceRushOrderController pController;
	
	@BeforeEach
	void setUp() throws Exception {
		pController = new PlaceRushOrderController();
	}

	@ParameterizedTest
    @CsvSource({
            "01-02-2021 14:00,false",
            "03-02-2022 14:22,true",
            "33-08-2020 14:00,false",
            "03-02-2021 25:01,false",
            "abc,false",
            "33-08-2021 14:00,false"
    })
	
	void test(String time, boolean expected) {
		boolean isValid = pController.validateReceiveTime(time);
		assertEquals(isValid, expected);
	}

}
