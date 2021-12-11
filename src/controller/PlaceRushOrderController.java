package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import utils.Configs;

/**
 * controller place rush order
 * @author TuyenTV_20184012
 *
 */
public class PlaceRushOrderController {

	/**
	 * formatter time
	 */
	public static final String RECEIVE_TIME_FORMATTER = "dd-MM-yyyy HH:mm";
	
	 public PlaceRushOrderController() {
		 
	 }
	/**
	 * check location is supported place rush order
	 * @param location
	 * @return boolean validate
	 */
	public boolean checkLocationSupportRushOrder(String location) {
		//check null or empty
		if (location == null || location.length() == 0 ) return false;
		
		// get provinces array support rush order
		String[] provinces_supported = Configs.PROVINCES_SUPPORT_RUSH_ORDER;
		
		//check support location
		for (int i=0 ;i <= provinces_supported.length - 1; i++) {
			if (provinces_supported[i] == location) return true;
		}
		return false;
	}
	
	/**
	 * check media is supported rush order
	 * @param id media id
	 * @return 
	 */
	public boolean checkMediaSupportRushOrder(int id) {
		//gia su co mot so media ho tro giao nhanh
		if (id == 38 || id == 41 || id == 44) return true;
		return false;
	}
	
	/**
	 * check support  place rush order
	 * @param location :location delivery
	 * @param id media id
	 * @return
	 */
	public boolean checkSupportRushOrder(String location, int id) {
		return checkLocationSupportRushOrder(location) && checkMediaSupportRushOrder(id);
	}
	
	/**
	 * method validate place rush order info 
	 * @param info
	 * @return value validate info
	 */
	public boolean validateRushOrderInfo(String info) {
		if (info == null || info.length() == 0) return false;
		for (char c: info.toCharArray()) {
			if (c ==' ' || c==',' || c == '.' || Character.isLetter(c)) {
				continue;
			}
			else return false;
		}
		return true;
	}
	
	/**
	 * method validate place rush order instruction 
	 * @param instruction
	 * @return value validate instruction
	 */
	public boolean validateInstruction(String instruction) {
		if (instruction == null || instruction.length() == 0) return false;
		for (char c: instruction.toCharArray()) {
			if (c ==' ' || c==',' || c=='.' || Character.isLetter(c)) {
				continue;
			}
			else return false;
		}
		return true;
	}
	
	/**
	 * method validate receive time
	 * @param receiveTime
	 * @return validate time
	 */
	public boolean validateReceiveTime(String receiveTime) {
		try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RECEIVE_TIME_FORMATTER);
            LocalDateTime time = LocalDateTime.parse(receiveTime, formatter);
            
            //check receive time after current time
            LocalDateTime currentDateTime = LocalDateTime.now();
            if(time.isAfter(currentDateTime)) return true;
            	return false;
        } catch (Exception e) {
            return false;
        }
	}
}
