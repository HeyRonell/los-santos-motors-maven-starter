package com.losantos.models;

/** MISSION 2: Calculate tax, fees, and optional financed monthly payments. */
public class SalesContract extends Contract {
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail,
                         Vehicle vehicle, boolean finance) {
        super(date, customerName, customerEmail, vehicle);
        this.finance = finance;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        // vehicle price + 5% tax + $100 recording fee + processing fee
        double vehiclePrice = getVehicle().getPrice();
        double salesTax = vehiclePrice * .05;
        int recordingFee = 100;
        double processingFee = (vehiclePrice < 10000) ? 295.0 : 495.0;
        return vehiclePrice + salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        // return 0 when not financed; otherwise amortize the full total
        if (!finance) {
            return 0;
        }

        double totalAmount = getTotalPrice();
        double vehiclePrice = getVehicle().getPrice();

        // financing info: 5% for 48 months, 4% for 24 months
        double interestRate;
        int months;

        if (vehiclePrice >= 10000) {
            interestRate = .05;
            months = 48;
        } else {
            interestRate = .04;
            months = 24;
        }

        // Formula for standard loans
        // monthly = P × r / (1 − (1 + r)^(−n))
        // P = amount being financed r = monthly interest rate n = number of months
        double P = totalAmount;
        double r = interestRate / 12;
        int n = months;
        // num/denom  P × r  / 1 - (1 + r)^(-n)
        double numerator = P * r;
        double denominator = 1 - Math.pow(1 + r, -n);

        return numerator / denominator;
    }
}