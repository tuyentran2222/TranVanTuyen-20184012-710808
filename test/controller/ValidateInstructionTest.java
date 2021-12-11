package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * test instruction delivery
 * @author TuyenTV_20184012
 *
 */
class ValidateInstructionTest {
	private PlaceRushOrderController pController;
	
	@BeforeEach
	void setUp() throws Exception {
		pController = new PlaceRushOrderController();
	}

	@ParameterizedTest
    @CsvSource({
            "Test instruction,true",
            "123s,false"
    })
	
	void test(String instruction, boolean expected) {
        boolean isValid = pController.validateInstruction(instruction);
        assertEquals(isValid, expected);
    }

}
