package test.example;

import util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

public class ConnectToDBTest {

    private Connection connection ;

    @Test(priority=1)
	public void connectToDb(){
		//Step 1: get db credentials 
		String testdb_ioserverdb=Database.getTestdbIoserverdb();
		String dbUser = Database.getDbuser();
		String dbPswd=Database.getDbpswd();
		//Step 2: connect to db and get Connection
		 connection = Database.connectToDB(testdb_ioserverdb,dbUser,dbPswd);
	}
	
    @Test (dependsOnMethods="connectToDb")
  	public void runSelectSQLStatementGetArrayList(){
  		//Step 3: create SQL statement and save it sin String 
  		String updateEmail =
  				"UPDATE ioserverdb.emailaddresses"
  						+ " SET emailAddress='inez2209@gmail.comnotvalid'"
  						+ " WHERE emailAddress='inez2209@gmail.com'\");\r\n";

  		//userId column
  		String selectUser=
  				"SELECT *\r\n" + 
  						"FROM ioserverdb.users u\r\n" + 
  						"LEFT JOIN ioserverdb.emailaddresses ea\r\n" + 
  						"ON u.emailAddressId=ea.emailAddressId\r\n" + 
  						"WHERE ea.emailAddress LIKE 'inessag08@gmail.com%'\r\n" + 
  						"OR emailAddress LIKE 'inez2209@gmail.com%';\r\n";

  		String deleteEmail=
  				"DELETE FROM ioserverdb.emailaddresses \r\n" + 
  						"WHERE emailAddress LIKE 'inessag08@gmail.com%'\r\n" + 
  						"OR emailAddress LIKE 'inez2209@gmail.com%';";

  		//Step 4: SELECT data and get it as ArrayList OR do Step 4A
  		ArrayList<String> usersTableArr = Database.getTestData(connection,selectUser);
  		
  	}
    
  
    @Test (dependsOnMethods="connectToDb")
	public void runUpdateSQLStatement(){
		
		//Step 5: UPDATE data -> run updateTestData which returns num of affected row		
		String email="test126101@yopmail.com";
		String insertEmail="INSERT INTO ioserverdb.emailAddresses (emailAddress, emailOptedIn, siteId)" + 
				"VALUES ('"+email+"', 1, 1)";
		
		String updateEmailOptedIn ="UPDATE ioserverdb.emailaddresses e" + 
				"SET e.emailOptedIn =0" + 
				"WHERE e.emailAddress LIKE '"+email+"'";

		Database.updateTestData(connection, insertEmail);
		Database.updateTestData(connection, updateEmailOptedIn);

	}
	
    @Test (dependsOnMethods="connectToDb")
	public void runInsertSQLStatment(){
		
		String emailAddressId="1428";
		//insert user : SHA is for password nautica1
		String insertUser=" INSERT INTO ioserverdb.users (siteId, member, emailAddressId, passwordSHA256, firstName)\r\n" + 
				"VALUES (1,0,"+emailAddressId+",'104626daf205e11508e59cf6f5a53b50b50e75ec6e8751aa1249752eef04029a', 'autoTest');";

		Database.updateTestData(connection, insertUser);
	}

    @Test (dependsOnMethods="connectToDb")
	public void runSQLStatementGetResultSet(){

		//emailAddressId column
		String selectEmails =
				"SELECT* FROM ioserverdb.emailaddresses \r\n" + 
						"WHERE emailAddress LIKE 'inessag08@gmail.com%'\r\n" + 
						"OR emailAddress LIKE 'inez2209@gmail.com%';\r\n";

		//Step 4A: SELECT data and get it as ResultSet
		ResultSet usersTable = Database.getTestDataAsResultSet(connection, selectEmails);
		System.out.println(usersTable);
	}
	
    @Test (dependsOnMethods="connectToDb")
	public void convertResultSetToList(){
		//emailAddressId column
				String selectEmails =
						"SELECT* FROM ioserverdb.emailaddresses \r\n" + 
								"WHERE emailAddress LIKE 'inessag08@gmail.com%'\r\n" + 
								"OR emailAddress LIKE 'inez2209@gmail.com%';\r\n";
		//Step 4A: SELECT data and get it as ResultSet
				ResultSet usersTable = Database.getTestDataAsResultSet(connection, selectEmails);
				//Step 4B: if ResultSet has multiple rows?
				List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
				try {
					list=Database.convertResultSetToList(usersTable);
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
}
