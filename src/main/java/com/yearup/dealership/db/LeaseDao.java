package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;
import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        // TODO: Implement the logic to add a lease contract
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("lease_contracts.csv", true))) {
            bw.write(leaseContract.getContractId() + "|" + leaseContract.getVin()
                    + "|" + leaseContract.getLeaseStart() + "|" + leaseContract.getLeaseEnd() + "|" + leaseContract.getMonthlyPayment());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


}
}