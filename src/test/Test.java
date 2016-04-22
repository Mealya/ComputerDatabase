package test;

import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.excilys.dao.ComputerDAO;
import com.excilys.database.JDBCTool;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.HeavyComputerDAO;
import com.excilys.utils.Pageable;

public class Test {

    public static void main(String[] argv) {
        String computerDBName = "computer-database-db";

        JDBCTool tool = new JDBCTool();
        tool.linkToMySql();

        ComputerDAO compt = new ComputerDAO();
        compt.setConnexion(tool);
        HeavyComputerDAO workingDB = new HeavyComputerDAO(compt);

        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        int choix = -1;
        while (choix != 0) {

            System.out.println("\nVeuillez saisir un nombre :");
            System.out.println("1- GetAll Java");
            System.out.println("2- GetAll SQL");
            System.out.println("3- Get : ID (int)");
            System.out.println("4- Update : ID (int)");
            System.out.println("5- Create");
            System.out.println("6- Delete");
            choix = sc.nextInt();

            
            //compt.setConnexion(tool.getConnection(computerDBName));
            List<Computer> data = workingDB.getComputers();
            tool.closeConnect(computerDBName);

            switch (choix) {
            case 1:
                /*
                //compt.setConnexion(tool.getConnection(computerDBName));
                Pageable<Computer> page = new Pageable<Computer>(
                        workingDB.getComputers());
                tool.closeConnect(computerDBName);
                page.setPage(0);
                for (int i = 0; i < page.getMaxPages(); i++) {
                    page.setPage(i);
                    // System.out.println("Index : " + i + " Max page : " +
                    // page.getMaxPages());
                    for (Computer c : page.getListForPage()) {
                        System.out.print(c.getName() + " "
                                + c.getIntro() + " " + c.getDisco() + " "
                                );
                        if (c.getComp() != null) {
                            System.out.println(c.getComp().getName());
                        } else {
                            System.out.print("\n");
                        }
                    }
                    if (page.getMaxPages() == (i - 1)) {
                        break;
                    }
                    System.out.println("\nNext page ? 0 : no / other : yes");

                    int pagingExist = sc2.nextInt();
                    if (pagingExist == 0) {
                        break;
                    }
                }
                */
                break;
            case 2:
                int nbPages = data.size() / 15;

                for (int i = 0; i < nbPages; i++) {
                    
                    //compt.setConnexion(tool.getConnection(computerDBName));
                    // System.out.println("Index : " + i + " Max page : " +
                    // page.getMaxPages());
                    for (Computer c : workingDB.getSetComputer((i * 15),
                            15 + (i * 15))) {
                        System.out.print(c.getName() + " "
                                + c.getIntro() + " " + c.getDisco() + " "
                                );
                        if (c.getComp() != null) {
                            System.out.println(c.getComp().getName());
                        } else {
                            System.out.print("\n");
                        }
                    }
                    tool.closeConnect(computerDBName);
                    System.out.println("\nNext page ? 0 : no / other : yes");

                    int pagingExist = sc2.nextInt();
                    if (pagingExist == 0) {
                        break;
                    }
                }
                break;
            case 3:
                System.out.println("Choisir un ID : ");
                long idCompu = sc.nextLong();
                
                //compt.setConnexion(tool.getConnection(computerDBName));
                System.out.println(workingDB.getComputer(idCompu).toString());
                tool.closeConnect(computerDBName);
                break;
            case 4:
                System.out.println("3- Update : choisir index");
                long id = sc.nextLong();
                for (Computer c : data) {
                    if (c.getId() == id) {
                        System.out.print("Actual : " + c.getName() + " "
                                + c.getIntro() + " " + c.getDisco() + " "
                                );
                        if (c.getComp() != null) {
                            System.out.println(c.getComp().getName());
                        }
                    }
                }
                Computer update = new Computer();
                update.setId(id);
                System.out.println("Nom :");
                update.setName(sc.next());
                System.out.println("Introduced :");
                try {
                    update.setIntro(new Timestamp(sc.nextInt()));
                } catch (InputMismatchException e) {
                    update.setIntro(null);
                    sc = new Scanner(System.in);
                }
                System.out.println("Disco :");

                try {
                    update.setDisco(new Timestamp(sc.nextInt()));
                } catch (InputMismatchException e) {
                    update.setDisco(null);
                    sc = new Scanner(System.in);
                }
                System.out.println("ID company :");

                try {
                    Company company = new Company();
                    company.setId(sc.nextInt());
                    update.setComp(company);
                } catch (InputMismatchException e) {
                    sc = new Scanner(System.in);
                }

                
                //compt.setConnexion(tool.getConnection(computerDBName));
                workingDB.updateComputer(update);
                tool.closeConnect(computerDBName);

                break;
            case 5:
                Computer create = new Computer();
                create.setId(0);
                System.out.println("Nom :");
                create.setName(sc.next());

                System.out.println("Introduced :");
                try {
                    create.setIntro(new Timestamp(sc.nextInt()));
                } catch (InputMismatchException e) {
                    create.setIntro(null);
                    sc = new Scanner(System.in);
                }

                System.out.println("Disco :");
                try {
                    create.setDisco(new Timestamp(sc.nextInt()));
                } catch (InputMismatchException e) {
                    create.setDisco(null);
                    sc = new Scanner(System.in);
                }

                System.out.println("ID company :");
                try {
                    Company company = new Company();
                    company.setId(sc.nextInt());
                    create.setComp(company);
                } catch (InputMismatchException e) {
                    sc = new Scanner(System.in);
                }

                
                //compt.setConnexion(tool.getConnection(computerDBName));
                workingDB.createComputer(create);
                tool.closeConnect(computerDBName);

                break;
            case 6:
                long idCompuDel = sc.nextLong();
                
                //compt.setConnexion(tool.getConnection(computerDBName));
                workingDB.deleteComputer(idCompuDel);
                tool.closeConnect(computerDBName);
                break;
            }
            sc = new Scanner(System.in);

        }
        sc.close();
        sc2.close();
    }
}

/*
 * for (Computer c : workingDB.getComputers()) {
 * System.out.println(c.toString()); } tool.closeConnect(computerDBName);
 * 
 * Computer newComputer = new Computer(0, "nouveauTest", new Timestamp(2502),
 * new Timestamp(5000), 1); tool.closeConnect(computerDBName);
 * 
 * tool.connectToMySql(computerDBName);
 * compt.setConnexion(tool.getConnection(computerDBName));
 * workingDB.createComputer(newComputer); tool.closeConnect(computerDBName);
 * 
 * tool.connectToMySql(computerDBName);
 * compt.setConnexion(tool.getConnection(computerDBName)); List<Computer>
 * computers = workingDB.getComputers(); tool.closeConnect(computerDBName);
 * 
 * tool.connectToMySql(computerDBName);
 * compt.setConnexion(tool.getConnection(computerDBName));
 * workingDB.deleteComputer(computers.get(computers.size()-1).getId());
 * tool.closeConnect(computerDBName);
 * 
 * tool.connectToMySql(computerDBName);
 * compt.setConnexion(tool.getConnection(computerDBName));
 * computers.get(computers.size()-2).setName("NomEdite");
 * workingDB.updateComputer(computers.get(computers.size()-2));
 * tool.closeConnect(computerDBName);
 */
