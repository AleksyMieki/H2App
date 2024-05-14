package com.example.H2DATABASE;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootApplication
public class H2DatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2DatabaseApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner console(DataSource dataSource) {
//		return (args) -> {
//			TimerThread t = new TimerThread(10000);
//			Scanner scanner = new Scanner(System.in);
//			String input = "";
//
//			while (!input.equals("exit")) {
//				ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//				executor.scheduleAtFixedRate(() -> {
//					String fileName = "monitFile.txt";
//					FileWriter fileWriter = null; // true to append to file
//					try {
//						fileWriter = new FileWriter(fileName, true);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//
//					try {
//						bufferedWriter.write("Next payment is due");
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//					try {
//						bufferedWriter.newLine(); // add a newline character
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//					try {
//						bufferedWriter.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}, 0, 10, TimeUnit.SECONDS);
//				// Print the available options
//				System.out.println("Select an option:");
//				System.out.println("1. Viev name and an id of a client ");
//				System.out.println("2. View data for a specific client");
//				System.out.println("3. Add a new client");
//				System.out.println("4. Add a new installation for a client");
//				System.out.println("5. Add a new pricing for a service type");
//				System.out.println("6. Calculate and log the bill for a specific installation");
//				System.out.println("7. View all payments for a specific installation");
//				System.out.println("8. Register a new payment for a specific installation");
//				System.out.println("9. Apply corrections to an existing payment");
//				System.out.println("Type 'exit' to quit.");
//				// Accept user input from the console
//				input = scanner.nextLine();
//
//				switch (input) {
//					case "1":
//						try (Connection connection = dataSource.getConnection()) {
//							String sql = "SELECT " + "*" + " FROM Klient";
//							PreparedStatement statement = connection.prepareStatement(sql);
//							ResultSet rs = statement.executeQuery();
//							while (rs.next()) {
//								System.out.println(rs.getString("imie") + " " + rs.getString("id"));
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//						break;
//					case "2":
//						// View data from a specific column in the Klient table
//						System.out.println("Type a column name to fetch its data (type 'exit' to quit): ");
//						String columnName = scanner.nextLine();
//						if (!columnName.equals("exit")) {
//							try (Connection connection = dataSource.getConnection()) {
//								String sql = "SELECT " + columnName + " FROM Klient";
//								PreparedStatement statement = connection.prepareStatement(sql);
//								ResultSet rs = statement.executeQuery();
//								while (rs.next()) {
//									String columnData = rs.getString(columnName);
//									System.out.println(columnData);
//								}
//							} catch (SQLException e) {
//								e.printStackTrace();
//							}
//						}
//						break;
//					case "3":
//						// Add data to the Klient table
//						//TODO add id to be able to insert values
//						try (Connection connection = dataSource.getConnection()) {
//							System.out.println("Enter the client's first name:");
//							String firstName = scanner.nextLine();
//							System.out.println("Enter the client's last name:");
//							String lastName = scanner.nextLine();
//							System.out.println("Enter the client's number:");
//							int clientNumber = Integer.parseInt(scanner.nextLine());
//							String sql = "INSERT INTO Klient (imie, nazwisko, numer_klienta) VALUES (?, ?, ?)";
//							PreparedStatement statement = connection.prepareStatement(sql);
//							statement.setString(1, firstName);
//							statement.setString(2, lastName);
//							statement.setInt(3, clientNumber);
//							int rowsInserted = statement.executeUpdate();
//							if (rowsInserted > 0) {
//								System.out.println("A new client was inserted successfully!");
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//
//						break;
//					case "4":
//						// Add a new installation for a client
//						try (Connection connection = dataSource.getConnection()) {
//							System.out.println("Enter the client's ID:");
//							int clientID = Integer.parseInt(scanner.nextLine());
//							System.out.println("Enter the installation address:");
//							String address = scanner.nextLine();
//							System.out.println("Enter the router number:");
//							int routerNumber = Integer.parseInt(scanner.nextLine());
//							System.out.println("Enter the service type:");
//							String serviceType = scanner.nextLine();
//
//							String sql = "INSERT INTO Instalacja (adres, numer_routera, typ_uslugi, klient_ID) VALUES (?, ?, ?, ?)";
//							PreparedStatement statement = connection.prepareStatement(sql);
//							statement.setString(1, address);
//							statement.setInt(2, routerNumber);
//							statement.setString(3, serviceType);
//							statement.setInt(4, clientID);
//							int rowsInserted = statement.executeUpdate();
//							if (rowsInserted > 0) {
//								System.out.println("A new installation was inserted successfully!");
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//						break;
//					case "5":
//						// Add a new pricing for a service type
//						System.out.println("Enter the service type:");
//						String serviceType = scanner.nextLine();
//						System.out.println("Enter the price:");
//						double price = scanner.nextDouble();
//						scanner.nextLine(); // Consume the remaining newline character
//						try (Connection connection = dataSource.getConnection()) {
//							String sql = "INSERT INTO Cennik (typ_uslugi, cena) VALUES (?, ?)";
//							PreparedStatement statement = connection.prepareStatement(sql);
//							statement.setString(1, serviceType);
//							statement.setDouble(2, price);
//							int rowsInserted = statement.executeUpdate();
//							if (rowsInserted > 0) {
//								System.out.println("A new pricing has been added to the Cennik table.");
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//						break;
//					case "6":
//						// Calculate and log the bill for a specific installation
//						System.out.println("Enter the installation ID: ");
//						int installationId = scanner.nextInt();
//
//						try (Connection connection = dataSource.getConnection()) {
//							// Retrieve the installation data
//							String installationSql = "SELECT * FROM Instalacja WHERE id = ?";
//							PreparedStatement installationStatement = connection.prepareStatement(installationSql);
//							installationStatement.setInt(1, installationId);
//							ResultSet installationResult = installationStatement.executeQuery();
//
//							// Retrieve the pricing data
//							String pricingSql = "SELECT cena FROM Cennik WHERE typ_uslugi = ?";
//							PreparedStatement pricingStatement = connection.prepareStatement(pricingSql);
//							String sT = installationResult.getString("typ_uslugi");
//							pricingStatement.setString(1, sT);
//							ResultSet pricingResult = pricingStatement.executeQuery();
//
//							if (installationResult.next() && pricingResult.next()) {
//								// Calculate the bill
//								double pricePerMonth = pricingResult.getDouble("cena");
//								double totalBill = pricePerMonth * 12;
//
//								// Log the bill to a file
//								String logMessage = "Installation " + installationId + " for service type " + sT + " has a total bill of " + totalBill;
//
//								System.out.println(logMessage);
//							} else {
//								System.out.println("Installation ID or service type not found.");
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//						break;
//					case "7":
//						// View all payments for a specific installation
//						System.out.println("Enter the client's ID: ");
//						int clientID = Integer.parseInt(scanner.nextLine());
//
//						try (Connection connection = dataSource.getConnection()) {
//							String sql = "SELECT * FROM Instalacja WHERE klient_id = ?";
//							PreparedStatement statement = connection.prepareStatement(sql);
//							statement.setInt(1, clientID);
//							ResultSet rs = statement.executeQuery();
//
//							while (rs.next()) {
//								String installationID = rs.getString("ID");
//								String address = rs.getString("ADRES");
//								String st= rs.getString("NUMER_ROUTERA");
//								String pr = rs.getString("TYP_USLUGI");
//
//
//								// Display the payment information to the console
//								System.out.println("Installation ID: " + installationID);
//								System.out.println("Address: " + address);
//								System.out.println("Router Number: " + st);
//								System.out.println("Type of service: " + pr);
//								System.out.println();
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//
//						break;
//					case "8":
//						// Register a new payment for a specific installation
//						System.out.println("Enter the ID of the installation to register payment for:");
//						int instid = Integer.parseInt(scanner.nextLine());
//
//						// Check if the installation exists
//						try (Connection connection = dataSource.getConnection()) {
//							String sql = "SELECT * FROM Instalacja WHERE id = ?";
//							PreparedStatement statement = connection.prepareStatement(sql);
//							statement.setInt(1, instid);
//							ResultSet rs = statement.executeQuery();
//
//							if (!rs.next()) {
//								System.out.println("Installation with ID " + instid + " does not exist.");
//								break;
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//
//						// Prompt the user to enter the payment amount
//						System.out.println("Enter the payment amount:");
//						double paymentAmount = Double.parseDouble(scanner.nextLine());
//
//						// Prompt the user to enter the payment date
//						System.out.println("Enter the payment date (format: yyyy-mm-dd):");
//						String paymentDate = scanner.nextLine();
//
//						// Insert the payment into the database
//						try (Connection connection = dataSource.getConnection()) {
//							String sql = "INSERT INTO `Dokonane wpłaty` (id_instalacji, termin_wplaty, kwota_wplaty) VALUES (?, ?, ?)";
//							PreparedStatement statement = connection.prepareStatement(sql);
//							statement.setInt(1, instid);
//							statement.setString(2, paymentDate);
//							statement.setDouble(3, paymentAmount);
//							statement.executeUpdate();
//							System.out.println("Payment registered successfully.");
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//						break;
//					case "9":
//						// Apply corrections to an existing payment
//						System.out.println("Enter the ID of the payment to correct:");
//						int paymentId = scanner.nextInt();
//						scanner.nextLine(); // consume the newline character
//
//						try (Connection connection = dataSource.getConnection()) {
//							// Retrieve the payment record from the database
//							PreparedStatement selectStatement = connection.prepareStatement(
//									"SELECT * FROM Dokonane_wplaty WHERE id = ?");
//							selectStatement.setInt(1, paymentId);
//							ResultSet rs = selectStatement.executeQuery();
//
//							if (rs.next()) {
//								// Display the details of the selected payment
//								System.out.println("Current payment details:");
//								System.out.println("ID: " + rs.getInt("id"));
//								System.out.println("Installation ID: " + rs.getInt("instalacja_id"));
//								System.out.println("Payment date: " + rs.getString("termin_wplaty"));
//								System.out.println("Payment amount: " + rs.getDouble("kwota_wplaty"));
//
//								// Prompt the user to enter the corrected payment amount
//								System.out.println("Enter the corrected payment amount:");
//								double correctedAmount = scanner.nextDouble();
//								scanner.nextLine(); // consume the newline character
//
//								// Update the payment record in the database
//								PreparedStatement updateStatement = connection.prepareStatement(
//										"UPDATE Dokonane_wplaty SET kwota_wplaty = ? WHERE id = ?");
//								updateStatement.setDouble(1, correctedAmount);
//								updateStatement.setInt(2, paymentId);
//								int rowsUpdated = updateStatement.executeUpdate();
//
//								if (rowsUpdated > 0) {
//									System.out.println("Payment corrected successfully.");
//								} else {
//									System.out.println("Failed to correct payment. Please try again.");
//								}
//							} else {
//								System.out.println("Payment not found. Please try again.");
//							}
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//						break;
//					case "exit":
//						// Exit the program
//						break;
//					default:
//						System.out.println("Invalid input. Please try again.");
//						break;
//				}
//			}
//
//			scanner.close();
//		};
//	}
}

