package com.losantos.data;

import com.losantos.models.Contract;
import com.losantos.models.LeaseContract;
import com.losantos.models.SalesContract;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/** MISSION 4: Append sale and lease contracts to a single file. */
public class ContractFileManager {
    private final Path file;

    public ContractFileManager(Path file) { this.file = file; }

    public void saveContract(Contract contract) throws IOException {
        String contractType;

        if (contract instanceof SalesContract) {
            contractType = "SALE";
        } else if (contract instanceof LeaseContract) {
            contractType = "LEASE";
        } else {
            throw new IllegalArgumentException("Unknown contract type");
        }

        String record = contractType + "|"
                + contract.getDate() + "|"
                + contract.getCustomerName() + "|"
                + contract.getCustomerEmail() + "|"
                + contract.getVehicle().toFileRecord().substring(
                0,
                contract.getVehicle().toFileRecord().lastIndexOf("|") + 1
        )
                + String.format(java.util.Locale.US, "%.2f",
                contract.getVehicle().getPrice());
        double vehiclePrice = contract.getVehicle().getPrice();

        if(contract instanceof SalesContract sale) {
            double salesTax = vehiclePrice * 0.05;
            double recordingFee = 100.00;
            double processFee;

            if (vehiclePrice < 10000) {
                processFee = 295.00;
            } else {
                processFee = 495.00;
            }
            record += "|" + String.format(java.util.Locale.US, "%.2f", salesTax);
            record += "|" + String.format(java.util.Locale.US, "%.2f", recordingFee);
            record += "|" + String.format(java.util.Locale.US, "%.2f", processFee);
            record += "|" + String.format(java.util.Locale.US, "%.2f", sale.getTotalPrice());
            record += "|" + (sale.isFinance() ? "YES" : "NO");
            record += "|" + String.format(java.util.Locale.US, "%.2f", sale.getMonthlyPayment());
        } else if (contract instanceof LeaseContract lease) {
            double endingValue = vehiclePrice * 0.50;
            double leasingFee = vehiclePrice * 0.07;
            record += "|" + String.format(java.util.Locale.US, "%.2f", endingValue);
            record += "|" + String.format(java.util.Locale.US, "%.2f", leasingFee);
            record += "|" + String.format(java.util.Locale.US, "%.2f", lease.getTotalPrice());
            record += "|" + String.format(java.util.Locale.US, "%.2f", lease.getMonthlyPayment());
            
        }

        Path parent = file.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        Files.writeString(file, record + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        // TODO: use instanceof to distinguish SalesContract from LeaseContract.
        // TODO: format ALL required common and contract-specific fields separated by '|'.
        // TODO: append one record using Files.writeString(..., CREATE, APPEND).
        // IMPORTANT: do NOT overwrite older records.
//        throw new UnsupportedOperationException("MISSION 4: Implement contract persistence");
    }
}
