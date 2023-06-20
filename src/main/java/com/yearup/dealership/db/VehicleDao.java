package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        // TODO: Implement the logic to add a vehicle

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO car_dealership (make,model,year,color,vehicleType,odometer,price) VALUES (?,?,?,?,?,?,?);"
                     ,PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, vehicle.getMake());
            statement.setString(2, vehicle.getModel());
            statement.setInt(3, vehicle.getYear());
            statement.setString(4, vehicle.getColor());
            statement.setString(5, vehicle.getVehicleType());
            statement.setInt(6, vehicle.getOdometer());
            statement.setDouble(7,vehicle.getPrice());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int vin = generatedKeys.getInt(1);
                System.out.println("New VIN: " + vin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM car_dealership WHERE VIN = ?")) {
            statement.setInt(1, Integer.parseInt(VIN));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range
        String query = "SELECT * FROM vehicles WHERE price >= ? AND price <= ?";
        Vehicle vehicle;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Vehicle> vehicles = new ArrayList<>();
                while (resultSet.next()) {
                    vehicle = createVehicleFromResultSet(resultSet);
                    vehicles.add(vehicle);
                }
                return vehicles;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model
        String query = "SELECT * FROM vehicles WHERE make = ? AND model = ?";
        Vehicle vehicle;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, make);
            statement.setString(2, model);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Vehicle> vehicles = new ArrayList<>();
                while (resultSet.next()) {
                    vehicle = createVehicleFromResultSet(resultSet);
                    vehicles.add(vehicle);
                }
                return vehicles;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }


    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range
        String query = "SELECT * FROM vehicles WHERE year >= ? AND year <= ?";
        Vehicle vehicle;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, minYear);
            statement.setDouble(2, maxYear);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Vehicle> vehicles = new ArrayList<>();
                while (resultSet.next()) {
                    vehicle = createVehicleFromResultSet(resultSet);
                    vehicles.add(vehicle);
                }
                return vehicles;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        String query = "SELECT * FROM vehicles WHERE color = ? ";
        Vehicle vehicle;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, color);

            ResultSet resultSet = statement.executeQuery();
                List<Vehicle> vehicles = new ArrayList<>();
                while (resultSet.next()) {
                    vehicle = createVehicleFromResultSet(resultSet);
                    vehicles.add(vehicle);
                }
                return vehicles;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();


    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        String query = "SELECT * FROM vehicles WHERE odometer >= ? AND odometer <= ?";
        Vehicle vehicle;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, minMileage);
            statement.setDouble(2, maxMileage);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Vehicle> vehicles = new ArrayList<>();
                while (resultSet.next()) {
                    vehicle = createVehicleFromResultSet(resultSet);
                    vehicles.add(vehicle);
                }
                return vehicles;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
        String query = "SELECT * FROM vehicles WHERE type = ? ";
        Vehicle vehicle;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, type);

            ResultSet resultSet = statement.executeQuery();
            List<Vehicle> vehicles = new ArrayList<>();
            while (resultSet.next()) {
                vehicle = createVehicleFromResultSet(resultSet);
                vehicles.add(vehicle);
            }
            return vehicles;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
