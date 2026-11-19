package com.losantos.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.*;

/** MISSION 3: Calculate the lease amount and its 36-month payment. */
public class LeaseContract extends Contract {
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
    }

    @Override
    public double getTotalPrice() {
        // TODO: price - 50% expected ending value + 7% lease fee.
        double price = getVehicle().getPrice();
        double endingValue = price * 0.50; // 50% expected ending value
        double leaseFee = price * 0.07; // lease fee
        return price - endingValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        // TODO: amortize getTotalPrice() at 4% annual interest over 36 months.
        double totalPrice = getTotalPrice();

        double monthlyRate = 0.04 / 12; // converting the annual to a monthly rate.
        int months = 36;
        double factor = Math.pow(1 + monthlyRate, -months); // making the interest factoring.

        // this will calculate using the amortization formula.
        double monthlyPayment = totalPrice *
                (monthlyRate * factor) / (factor - 1);

        return monthlyPayment;
    }

    public static boolean isEligible() {

        boolean isEligible = false;

        int currentYear = Year.now().getValue();

        int vehicleYear = vehicle.getYear(); // current vehicle instance is not being pulled.

        int vehicleAge = currentYear - vehicleYear;

        if (vehicleAge >= 0 && vehicleAge <= 3) {
            isEligible = true;
        } else {
            System.out.println("This vehicle is not eligible for leasing.");
        }
        return isEligible;
    }
}
