package controller;

import entity.order.Order;
import java.util.Random;

/**
 * class tính phí vận chuyển có thêm trọng lượng
 * @author TuyenTV
 *
 */
public class ShippingFeeWithWeightCaculator implements ShippingFeeCalculator {

	@Override
	public int calculateShippingFee(Order order, int height, int length, int width) {
		int weight = height * length * width / 6000;
		Random rand = new Random();
        return (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() + weight);
	}

}
