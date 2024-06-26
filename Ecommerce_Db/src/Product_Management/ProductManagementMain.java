package Product_Management;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Common.DBUtils;  

public class ProductManagementMain {
	
	public void productManagement() throws IOException, SQLException {
		System.out.println(" #############  Welcome to Product Management ########## ");

		Scanner sc = new Scanner(System.in);

		boolean shallIKeepRunningCode = true;

		Product product = new Product(); 

		while (shallIKeepRunningCode) {

			System.out.println("     What would you like to do today ?");
			System.out.println("        1. Add Product");
			System.out.println("        2. Update Product");
			System.out.println("        3. Search Product");
			System.out.println("        4. Delete Product");
			System.out.println("        5. Print Product");
			System.out.println("        9. Exit");

			int option = sc.nextInt();

			switch (option) {
			case 1:
				System.out.println("Add Product");
				System.out.println("\nEnter the product details: ");

				System.out.println("Enter the product ID: ");
				product.productID = sc.nextInt();

				System.out.println("\nEnter the product name: ");
				product.productName = sc.next();

				System.out.println("Enter the product quantity: ");
				product.productQuantity = sc.nextInt();

				System.out.println("Enter the product price: ");
				product.productPrice = sc.nextInt();

				System.out.println("       Product added succefully!!!");

				String insertQuery = "INSERT INTO product (product_id, product_name, quantity, price) VALUES ("
						+ product.productID + ", '" + product.productName + "', " + product.productQuantity + ", "
						+ product.productPrice + ")";

				DBUtils.executeQuery(insertQuery);

				break;

			case 2:

				System.out.print("Enter ID to update: ");
				product.productID = sc.nextInt();
				
				System.out.print("Enter new name: ");
				String newName = sc.next();
				
				System.out.print("Enter new quantity: ");
				int newquantity = sc.nextInt();
				
				System.out.print("Enter new price: ");
				int newprice = sc.nextInt();
				String updateQuery = "UPDATE product SET product_name = '" + newName + "', quantity = " + newquantity
						+ ",price=" + newprice + " WHERE product_id = " +  product.productID;
				
				
				DBUtils.executeQuery(updateQuery);
				
				System.out.println("Record updated successfully.");
				break;
			case 3:

				System.out.print("Enter ID to search: ");
				product.productID = sc.nextInt();
				String searchQuery = "SELECT * FROM product WHERE product_id = " + product.productID;

				ResultSet result = DBUtils.executeSelectQuery(searchQuery);
				if (result.next()) {
					System.out.println("ID: " + result.getInt("product_id") + ", Name: "
							+ result.getString("product_name") + ", Quantity: " + result.getInt("quantity")
							+ ", Price: " + result.getInt("price"));
				} else {
					System.out.println("Record not found.");
				}
				break;

			case 4:

				System.out.print("Enter ID to delete: ");
				product.productID = sc.nextInt();

				String deleteQuery = "DELETE FROM product WHERE product_id = " + product.productID;

				DBUtils.executeQuery(deleteQuery);

				System.out.println("Record deleted successfully.");
				break;

			case 5:

				String viewAllQuery = "SELECT * FROM product";

				ResultSet result1 = DBUtils.executeSelectQuery(viewAllQuery);
				while (result1.next()) {
					System.out.println("ID: " + result1.getInt("product_id") + ", Name: "
							+ result1.getString("product_name") + ", quantity: " + result1.getInt("quantity")
							+ ", Price: " + result1.getInt("price"));
				}
				break;

			case 9:
				System.out.println("Exiting the product management...");
				shallIKeepRunningCode = false;
				break;

			default:
				System.out.println("Unknown option");
			}
		}
	}

}
