package test;

import java.sql.SQLException;
import java.sql.Timestamp;

import com.excilys.database.ComputerDB;
import com.excilys.database.HeavyComputerDB;
import com.excilys.database.JDBCTool;
import com.excilys.model.Computer;

public class Test {

	public static void main(String[] argv) {
		String computerDBName = "computer-database-db";
		
		JDBCTool tool = new JDBCTool();
		tool.connectToMySql(computerDBName);

		ComputerDB compt = new ComputerDB(tool.getConnection(computerDBName));
		HeavyComputerDB workingDB = new HeavyComputerDB(compt);
		
		for (Computer c : workingDB.getComputers()) {
			System.out.println(c.toString());
		}
		tool.closeConnect(computerDBName);

		Computer newComputer = new Computer(0, "nouveauTest", new Timestamp(2502), new Timestamp(5000), 1);
		tool.closeConnect(computerDBName);
		
		tool.connectToMySql(computerDBName);
		compt.setConnexion(tool.getConnection(computerDBName));
		workingDB.createComputer(newComputer);
		tool.closeConnect(computerDBName);
	}
}
