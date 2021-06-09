package main;

import java.util.logging.Logger;

public class Payment {
    public static final int WEEKDAYCHARGE = 200;
    public static final int WEEKENDCHARGE = 250;
    private int money;
    private double totalCharge;
	Logger logger = Logger.getLogger("Payment");
    public Payment(Discount discount, String dateTime) {

        String week = InputNormalization.extractWeek(dateTime);
        logger.info(week);

        switch (week) {
            case "週一":
            case "週二":
            case "週三":
            case "週四":
            case "週五":
                money = WEEKDAYCHARGE;
                break;
            case "週六":
			
			money = WEEKENDCHARGE;
                break;
			default:
				break;	
        }

        totalCharge = money * discount.getDiscount();
    }

    public void print() {
        System.out.println("Please pay $" + (int) totalCharge + ".");
    }

    public double getTotalCharge(){
        return totalCharge;
    }
}
