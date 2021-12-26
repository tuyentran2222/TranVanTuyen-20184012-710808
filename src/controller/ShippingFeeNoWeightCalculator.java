package controller;

import java.util.Random;

import entity.order.Order;

public class ShippingFeeNoWeightCalculator implements ShippingFeeCalculator{

	/**
	 * TuyenTV_20184012
	 * tính phí vận chuyển không quan tâm đến khối lượng
	 */
	@Override
	public int calculateShippingFee(Order order, int height, int length, int width) {
		Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        return fees;
	}

}
