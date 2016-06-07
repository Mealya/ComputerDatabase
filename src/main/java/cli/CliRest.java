package cli;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.excilys.model.Company;
import com.excilys.model.Computer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
 

public class CliRest {

    private static final String BASE_URL = "http://localhost:8080/ComputerDatabaseMaven/rest/";

    private static final String DASHBOARD = BASE_URL + "/";
    private static final String EDIT = BASE_URL + "/editComputer";
    private static final String DELETE = BASE_URL + "/deleteComputer";
    private static final String ADD = BASE_URL + "addComputer";
    
    private static Scanner sc = new Scanner(System.in);
    
    /*
    public void findComputer() throws IOException {
        System.out.println("Entrez un id :");
        long id = askLong();
        try {
            String requestUrl = String.format(FIND_COMPUTER_URL, id);
            String serverResponse = client.target(requestUrl).request(MediaType.APPLICATION_JSON_VALUE)
                    .get(String.class);
            Computer computer = mapper.readValue(serverResponse, Computer.class);            view.showComputerDetail(computer);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    } */
    
    public static void dashboard(int page) {
        Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
 
        WebTarget rootTarget = client.target(BASE_URL);

        Response response = rootTarget.request().get();
 
        System.out.println("Default endpoint");
        System.out.println("Response code: " + response.getStatus());
        System.out.println("Response: \n" + response.readEntity(String.class));
    }
    
    private static Long askLong() {
        Long l = sc.nextLong();
        sc.nextLine();        
        return l;
    }
    
    public static void main(String args[]) {
        BOUCLE_CLI : while (true) {
            System.out.println("\nVeuillez saisir un nombre :");
            System.out.println("0- Fin");
            System.out.println("1- DashBoard");
            System.out.println("2- Get : ID (int)");
            System.out.println("3- Edit computer : ID of computer (int)");
            System.out.println("4- Create a computer");
            System.out.println("5- Delete a computer");
            System.out.println("6- Delete company with computers");
            Long choix = askLong();
            
            switch (choix.intValue()) {
            case 0:
                break BOUCLE_CLI;
            case 1:
                dashboard(0);
                break;
            case 2:
              
                break;
            case 3:
                System.out.println("3- Update : choisir index");
               
                break;
            case 4:
         
                break;
            case 5:
        
                break;
            case 6:

                break;
           
            case 7:

                break;
            }
        }
        
        
    }

    
}
