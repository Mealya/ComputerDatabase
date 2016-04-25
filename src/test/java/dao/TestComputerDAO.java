package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.excilys.dao.ComputerDAO;
import com.excilys.database.JDBCTool;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class TestComputerDAO {

    // private JDBCTool tool;

    /*
     * @Before public void executedBeforeEach() { tool = new JDBCTool(); }
     * 
     * @After public void executedAfterEach() { tool = new JDBCTool(); }
     */

    @Test
    public void test_Constructor_Throw_Exception() {
        // Given
        ComputerDAO comp = null;
        JDBCTool testFail = null;

        try {
            // When
            comp = new ComputerDAO(testFail);
            fail("Must throw an exception");
        } catch (IllegalArgumentException e) {
            // Then
            assertEquals(e.getMessage(), "c is null");
        }
    }

    @Test
    public void test_GetAll() {
        // Given
        JDBCTool tool = new JDBCTool();
        ComputerDAO comp = new ComputerDAO(tool);
        comp.switchDB();
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
    /*
    @Test
    public void test_GetId() {
        // Given
        Computer comp = new Computer();
        comp.setId(1);ezs
        comp.setName("Apple Inc.");sdf
        JDBCTool tool = new JDBCTool();
        ComputerDAO compaDAO = new ComputerDAO(tool);
        compaDAO.switchDB();
        Computer result = null;

        // When
        result = compaDAO.get(1);

        // Then
        assertEquals(result.getId(), comp.getId());
        assertEquals(result.getName(), comp.getName());
    }

    @Test
    public void test_GetId_Throw_Exception_Id_Neg() {
        // Given
        JDBCTool tool = new JDBCTool();
        CompanyDAO compaDAO = new CompanyDAO(tool);
        compaDAO.switchDB();

        try {
            // When
            compaDAO.get(-1);
            fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
            // Then
            assertEquals(e.getMessage(), "ID should not be negative or 0");
        }

    }
    
    @Test
    public void test_GetId_Throw_Exception_Id_0() {
        // Given
        JDBCTool tool = new JDBCTool();
        CompanyDAO compaDAO = new CompanyDAO(tool);
        compaDAO.switchDB();

        try {
            // When
            compaDAO.get(0);
            fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
            // Then
            assertEquals(e.getMessage(), "ID should not be negative or 0");
        }

    }
    */
}
