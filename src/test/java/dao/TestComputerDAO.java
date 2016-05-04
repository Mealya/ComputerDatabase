package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.excilys.dao.ComputerDAO;
import com.excilys.database.BasicJdbc;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class TestComputerDAO {

    // private JDBCTool tool;

    /*
     * @Before public void executedBeforeEach() { tool = new JDBCTool(); }
     * 
     * @After public void executedAfterEach() { tool = new JDBCTool(); }
     */

    @Ignore
    @Test
    public void test_Constructor_Throw_Exception() {
        // Given
        ComputerDAO comp = null;
        try {
            // When
            comp = new ComputerDAO();
            fail("Must throw an exception");
        } catch (IllegalArgumentException e) {
            // Then
            assertEquals(e.getMessage(), "Tool is null");
        }
    }

    @Test
    public void test_GetAll() {
        // Given
        ComputerDAO comp = new ComputerDAO();
        List<Computer> listResult = null;

        // When
        listResult = comp.getAll();

        // Then
        if (listResult == null) {
            fail("The list should not be null");
        } else if (listResult.isEmpty()) {
            fail("The list should not be empty");
        }

    }
    
    @Test
    public void test_GetId() {
        //Given
        Computer comp = new Computer();
        comp.setId(2);
        comp.setName("CM-2a");
        comp.setIntro(null);
        comp.setDisco(null);
        
        Company c = new Company();
        c.setId(2);
        c.setName("Thinking Machines");
        comp.setComp(c);
        
        ComputerDAO compaDAO = new ComputerDAO();


        // When
        Computer result = compaDAO.get(2); 

        // Then
        assertEquals(comp.getId(), result.getId());
        assertEquals(comp.getName(), result.getName());
        assertEquals(comp.getDisco(), result.getDisco());
        assertEquals(comp.getIntro(), result.getIntro());
        assertEquals(comp.getComp().getId(), result.getComp().getId());
        assertEquals(comp.getComp().getName(), result.getComp().getName());
    }

    @Test
    public void test_GetId_Throw_Exception_Id_Neg() {
        // Given
        ComputerDAO compDAO = new ComputerDAO();
        try {
            // When
            compDAO.get(-1);
            fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
            // Then
            assertEquals(e.getMessage(), "ID should not be negative or 0");
        }

    }
    
    @Test
    public void test_GetId_Throw_Exception_Id_0() {
        // Given
        ComputerDAO compDAO = new ComputerDAO();

        try {
            // When
            compDAO.get(0);
            fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
            // Then
            assertEquals(e.getMessage(), "ID should not be negative or 0");
        }

    }
    
}
