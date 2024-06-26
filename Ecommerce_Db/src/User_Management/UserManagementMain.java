package User_Management;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Common.DBUtils;


public class UserManagementMain {
	
	public void userManagement() throws IOException, SQLException {
		System.out.println(" #############  Welcome to User Management ########## ");

		Scanner sc = new Scanner(System.in);

		boolean shallIKeepRunningCode = true;

		User user = new User();

		while (shallIKeepRunningCode) {

			System.out.println("     What would you like to do today ?");
			System.out.println("        1. Add User");
			System.out.println("        2. Update User ");
			System.out.println("        3. Search User");
			System.out.println("        4. Delete User");
			System.out.println("        5. Print User");
			System.out.println("        9. Exit");

			int option = sc.nextInt();

			switch (option) {
			case 1:
				System.out.println("Add User");
				System.out.println("\nEnter the User details: ");
				
				System.out.println("Enter user id : ");
				user.id = sc.nextInt();

				System.out.println("Enter the 1st name : ");
				user.firstName = sc.next();

				System.out.println("Enter the last name : ");
				user.lastName = sc.next();

				System.out.println("Enter the age : ");
				user.age = sc.nextInt();

				System.out.println("Enter the gender : ");
				user.gender = sc.next();

				System.out.println("Enter the email : ");
				user.email = sc.next();

				System.out.println("Enter the  Passward : ");
				user.Passward = sc.next();

				System.out.println("Enter the confirm passward : ");
				user.confirmPassward = sc.next();
				System.out.println("       Product added succefully!!!");

				String insertQuery = "INSERT INTO user (First_name, last_name, age, gender, Email, Passward) VALUES ( '"
						+ user.firstName + "','" + user.lastName + "'," + user.age + ",'" + user.gender + "', ' "
						+ user.email + "','" + user.Passward + "')";
				DBUtils.executeQuery(insertQuery);

				break;

			case 2:

				System.out.print("Enter ID to update: ");
				user.id = sc.nextInt();

				System.out.print("Enter new Firstname: ");
				String newName = sc.next();

				System.out.print("Enter new lastname: ");
				String newlastName = sc.next();

				System.out.print("Enter new age: ");
				int newage = sc.nextInt();

				System.out.print("Enter new gender: ");
				String newgender = sc.next();

				System.out.print("Enter new email: ");
				String newemail = sc.next();

				System.out.print("Enter new passward: ");
				String newpassward = sc.next();

				String updateQuery = "UPDATE user SET  First_name='" + newName + "', last_name='" + newlastName
						+ "', age=" + newage + ", gender='" + newgender + "', Email='" + newemail + "', Passward='"
						+ newpassward + "' WHERE id = " + user.id;

				DBUtils.executeQuery(updateQuery);

				System.out.println("Record updated successfully.");
				break;
			case 3:

				System.out.print("Enter ID to search: ");
				user.id = sc.nextInt();
				String searchQuery = "SELECT * FROM user WHERE id = " + user.id;

				ResultSet result = DBUtils.executeSelectQuery(searchQuery);
				if (result.next()) {
					System.out.println("ID: " + result.getInt("id") + ", First Name: "
							+ result.getString("First_name")+", last Name: "
									+ result.getString("last_name") + ", Age: " + result.getInt("age") + ", Gender: "
									+ result.getString("gender") + ", Passward: "
																			+ result.getString("Passward")
							);
				} else {
					System.out.println("Record not found.");
				}
				break;

			case 4:

				System.out.print("Enter ID to delete: ");
				user.id = sc.nextInt();

				String deleteQuery = "DELETE FROM user WHERE id = " + user.id;

				DBUtils.executeQuery(deleteQuery);

				System.out.println("Record deleted successfully.");
				break;

			case 5:

				String viewAllQuery = "SELECT * FROM user";

				ResultSet result1 = DBUtils.executeSelectQuery(viewAllQuery);
				while (result1.next()) {
					System.out.println("ID: " + result1.getInt("id") + ", First Name: "
							+ result1.getString("First_name")+", last Name: "
									+ result1.getString("last_name") + ", Age: " + result1.getInt("age") + ", Gender: "
									+ result1.getString("gender") + ", Passward: "
																			+ result1.getString("Passward")
							);
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
