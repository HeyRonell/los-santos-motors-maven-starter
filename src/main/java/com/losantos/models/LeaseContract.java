package com.losantos.models;

/** MISSION 3: Calculate the lease amount and its 36-month payment. */
public class LeaseContract extends Contract {
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
    }
    @Override
    public double getTotalPrice() {
        // TODO: price - 50% expected ending value + 7% lease fee.
        double price = getVehicle().getPrice();
        double endingValue = price * 0.50;
        double leaseFee = price * 0.07;
        double totalPrice = price - endingValue+leaseFee;
        return totalPrice;
       // throw new UnsupportedOperationException("MISSION 3: Implement lease total");
    }
    @Override
    public double getMonthlyPayment() {
        // TODO: amortize getTotalPrice() at 4% annual interest over 36 months.
        double principal = getTotalPrice();
        double monthlyInterest = 0.04 / 12;
        int months = 36;

        return principal *
                (monthlyInterest * Math.pow(1 + monthlyInterest, months))
                / (Math.pow(1 + monthlyInterest, months) -1 );

//        double payment = totalPrice / months;
//        payment = payment + (totalPrice * monthlyInterest);
//
//        return payment;
   //    throw new UnsupportedOperationException("MISSION 3: Implement lease payment");
    }
}
