package HospitalManagement;

import java.sql.*;

public class doctor {
	private Connection connection;
	
	public doctor(Connection connection) {
		this.connection = connection;
	}
	
	public void viewDoctors() {
		String query = "SELECT * FROM doctors";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Doctors: ");
			System.out.println("+-----------+------------------------+---------------------+");
			System.out.println("|Doctor Id  |Name                    |Specialization       |");
			System.out.println("+-----------+------------------------+---------------------+");
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String specialization = resultSet.getString("specialization");
				System.out.printf("|%-11s|%-24s|%-21s|\n", id, name, specialization);
				System.out.println("+-----------+------------------------+---------------------+");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getDoctorById(int id) {
		String query = "SELECT * FROM doctors WHERE ID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return true;
			} else {
				return false;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
