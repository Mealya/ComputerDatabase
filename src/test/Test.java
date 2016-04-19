package test;

import java.sql.SQLException;

import com.excilys.database.ComputerDB;
import com.excilys.database.JDBCTool;

public class Test {
	
	public static void main(String[] argv) throws SQLException {
        JDBCTool tool = new JDBCTool();
        tool.connectToMySql("computer-database-db");
       
        ComputerDB compt = new ComputerDB(tool.getConnection("computer-database-db"));
        System.out.println(compt.getComputers());
        
       tool.closeConnect("computer-database-db");
    }
}
