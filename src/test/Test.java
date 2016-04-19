package test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

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

        tool.connectToMySql(computerDBName);
        compt.setConnexion(tool.getConnection(computerDBName));
        List<Computer> computers = workingDB.getComputers();
        tool.closeConnect(computerDBName);

        tool.connectToMySql(computerDBName);
        compt.setConnexion(tool.getConnection(computerDBName));
        workingDB.deleteComputer(computers.get(computers.size()-1));
        tool.closeConnect(computerDBName);

        tool.connectToMySql(computerDBName);
        compt.setConnexion(tool.getConnection(computerDBName));
        computers.get(computers.size()-2).setName("NomEdite");
        workingDB.updateComputer(computers.get(computers.size()-2));
        tool.closeConnect(computerDBName);

        int choix = -1;
        while (choix != 0) {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nVeuillez saisir un nombre :");
            System.out.println("1- GetAll");
            System.out.println("2- Get : ID (int)");
            System.out.println("3- Update : ID (int)");
            System.out.println("4- Create");
            System.out.println("5- Delete");
            choix = sc.nextInt();
            
            tool.connectToMySql(computerDBName);
            compt.setConnexion(tool.getConnection(computerDBName));
            List<Computer> data = workingDB.getComputers();
            tool.closeConnect(computerDBName);
            
            switch (choix) {
            case 1 :
                tool.connectToMySql(computerDBName);
                compt.setConnexion(tool.getConnection(computerDBName));
                for (Computer c : workingDB.getComputers()) {
                    System.out.println(c.toString());
                }
                tool.closeConnect(computerDBName);
                break;
            case 2 : 
                
                break;
            case 3 : 
                System.out.println("3- Update : choisir index");
                int id = sc.nextInt();
                int index = 0;
                for (Computer c : data) {
                    if (c.getId() == id) {
                        System.out.println("Actual : " + c.getName() + " " + c.getIntro() + " " + c.getDisco() + " " + c.getCompId());
                    }
                }
                Computer update = new Computer();
                update.setId(id);
                System.out.println("Nom :");
                update.setName(sc.next());
                System.out.println("Introduced :");
                update.setIntro(new Timestamp(sc.nextInt()));
                System.out.println("Disco :");
                update.setDisco(new Timestamp(sc.nextInt()));
                System.out.println("ID company :");
                update.setCompId(sc.nextInt());
                
                tool.connectToMySql(computerDBName);
                compt.setConnexion(tool.getConnection(computerDBName));
                workingDB.updateComputer(update);
                tool.closeConnect(computerDBName);
                
                break;
            case 4 : 

                break;
            case 5 : 

                break;
            }
        }
    }
}
