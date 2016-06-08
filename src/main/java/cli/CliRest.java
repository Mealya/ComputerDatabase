package cli;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.Scanner;

import com.excilys.dto.AddComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
 

public class CliRest {

    private static final String BASE_URL = "http://localhost:8080/ComputerDatabaseMaven/rest/";

    private static final String DASHBOARD = BASE_URL + "dashboard";
    private static final String EDIT = BASE_URL + "editComputer";
    private static final String EDITFORM = EDIT + "Form?id=";
    private static final String DELETE = BASE_URL + "deleteComputer";
    private static final String ADD = BASE_URL + "addComputer";
    
    private static Scanner sc = new Scanner(System.in);
    private static Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
    
    
    private static String findComputer() {
        System.out.println("Entrez un id :");
        long id = askLong();
        String serverResponse = client.target(EDITFORM + id).request().get(String.class);
        //System.out.println(serverResponse);
        try {
            @SuppressWarnings("unchecked")
            HashMap<String, Object> result = new ObjectMapper().readValue(serverResponse, HashMap.class);
            System.out.println("===============");
            System.out.println("Current computer : ");
            System.out.println("Computer name : " + result.get("computerName"));
            System.out.println("Introduced date : " + result.get("introduced"));
            System.out.println("Discontinued date : " + result.get("introduced"));
            System.out.println("Company id : " + result.get("companyId"));
            System.out.println("===============");
            System.out.println("Company list : " + result.get("companies")); 
            
            /* for (Entry<String, Object> c : result.entrySet()) {
                if (c != null) {
                    System.out.print("\n " + c.getKey() + " : ");
                    System.out.print(c.getValue() + " \n");
                }
                
            }*/
            return (String) result.get("id");
            
        } catch (IOException e) {
            //TODO : what to do?
            return null;
        }         
    } 
    
    private static void dashboard(int page) {       
        WebTarget rootTarget = client.target(BASE_URL);

        Response response = rootTarget.request().get();
 
        System.out.println("Default endpoint");
        System.out.println("Response code: " + response.getStatus());
        System.out.println("Response: \n" + response.readEntity(String.class));
    }
    
    private static void addComputer(Form computer) {
        WebTarget rootTarget = client.target(ADD);
        Invocation.Builder builder = rootTarget.request();

        Response response = builder.accept(MediaType.APPLICATION_JSON).post(Entity.entity(computer, MediaType.APPLICATION_FORM_URLENCODED));
        
        if (response.getStatus() != HttpStatus.SC_OK) {
            System.out.println(response.toString());
            response.close();
        } else {
            System.out.println("\n Computer added : ");
            System.out.println(response.readEntity(String.class));
        }
    }
    private static void editComputer(Form computer) {
        WebTarget rootTarget = client.target(EDIT);
        Invocation.Builder builder = rootTarget.request();

        Response response = builder.accept(MediaType.APPLICATION_JSON).post(Entity.entity(computer, MediaType.APPLICATION_FORM_URLENCODED));
        
        if (response.getStatus() != HttpStatus.SC_OK) {
            System.out.println(response.toString());
            response.close();
        } else {
            System.out.println("\n Computer edited : ");
            System.out.println(response.readEntity(String.class));
        }
    }
    
    private static Form askForAddComputer() {
        Form form = new Form();
        System.out.println("Name :");
        //form.param("computerName", "Test");
        form.param("computerName", sc.next());
        
        System.out.println("Introduced :");
        //java.util.Date date= new java.util.Date();
        //Timestamp ts_now = new Timestamp(date.getTime());
        //form.param("introduced", ts_now.toString());
        form.param("introduced", sc.next());
        
        System.out.println("Discontinued :");
        //form.param("discontinued", ts_now.toString());
        form.param("introduced", sc.next());
        
        System.out.println("ID company :");
        //form.param("companyId", "1");
        form.param("companyId", "" + sc.nextInt());
        return form;
        
        
    }
    private static Form askForEditComputer() {
        Form form = new Form();
        String id = findComputer();
        System.out.println("Computer id : " + id);
        form.param("id", id);
        System.out.println("New name :");
        //form.param("computerName", "Test");
        form.param("computerName", sc.next());
        
        System.out.println("New introduced :");
        //java.util.Date date= new java.util.Date();
        //Timestamp ts_now = new Timestamp(date.getTime());
        //form.param("introduced", ts_now.toString());
        form.param("introduced", sc.next());
        
        System.out.println("New discontinued :");
        //form.param("discontinued", ts_now.toString());
        form.param("discontinued", sc.next());
        
        System.out.println("New ID company :");
        //form.param("companyId", "1");
        form.param("companyId", "" + sc.nextInt());
        return form;
    }
    
    private static Long askLong() {
        Long l = sc.nextLong();
        sc.nextLine();        
        return l;
    }
    
    public static void main(String args[]) {
        BOUCLE_CLI : while (true) {
            System.out.println("\nCli menu : ");
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
                //FIXME : page ?
                dashboard(0);
                break;
            case 2:
                throw new NotImplementedException();
                
                //break;
            case 3:
                //askForEditComputer();
                //throw new NotImplementedException();
                System.out.println("3- Edit computer :");
                editComputer(askForEditComputer());
                break;
            case 4:
                System.out.println("4- Create a computer :");
                addComputer(askForAddComputer());
                break;
            case 5:
                throw new NotImplementedException();
                //break;
            case 6:
                throw new NotImplementedException();
                //break;
            case 7:
                throw new NotImplementedException();
                //break;
            }
        }
    }
}

/* In case of need
AddComputerDTO create = new AddComputerDTO();
System.out.println("Nom :");
create.setComputerName(sc.next());

System.out.println("Introduced :");
try {
    create.setIntroduced(new Timestamp(sc.nextInt()).toString());
} catch (InputMismatchException e) {
    create.setIntroduced(null);
    sc = new Scanner(System.in);
}

System.out.println("Discontinued :");
try {
    create.setDiscontinued(new Timestamp(sc.nextInt()).toString());
} catch (InputMismatchException e) {
    create.setDiscontinued(null);
    sc = new Scanner(System.in);
}

System.out.println("ID company :");
try {
    create.setCompanyId("" + sc.nextInt());
} catch (InputMismatchException e) {
    create.setCompanyId("1");
}
return create;*/