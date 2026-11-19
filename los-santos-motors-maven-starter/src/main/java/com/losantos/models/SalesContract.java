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
        // TODO: vehicle price + 5% tax + $100 recording fee + processing fee.
        double vehiclePrice = getVehicle().getPrice();
        double salesTax = vehiclePrice * .10;
        int recordingFee = 100;
        double processingFee = (vehiclePrice < 10000) ? 295.0 : 495.0;
        return vehiclePrice + salesTax + recordingFee + processingFee;
        //throw new UnsupportedOperationException("MISSION 2: Implement sales payment");

    }

    @Override
    public double getMonthlyPayment() {
        // TODO: return 0 when not financed; otherwise amortize the full total.
        //throw new UnsupportedOperationException("MISSION 2: Implement sales payment");
        double totalAmount = getTotalPrice();
        double vehiclePrice = getVehicle().getPrice();

        // financing info lets use a standard 5% interest 48-Month Term, 4% for 24-month
        double interestRate;
        int months;

        if (vehiclePrice >= 10000){
            interestRate = .05;
            months = 48;
        }



