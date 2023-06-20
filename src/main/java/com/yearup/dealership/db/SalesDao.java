package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        // TODO: Implement the logic to add a sales contract
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("lease_contracts.csv", true))) {
            bw.write(salesContract.getContractId() + "|" + salesContract.getVin()
                    + "|" + salesContract.getSaleDate() + "|" + salesContract.getPrice());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
