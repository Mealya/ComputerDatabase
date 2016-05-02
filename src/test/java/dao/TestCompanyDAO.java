package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.excilys.dao.CompanyDAO;
import com.excilys.database.BasicJdbc;
import com.excilys.model.Company;

public class TestCompanyDAO {

    // private JDBCTool tool;

    /*
     * @Before public void executedBeforeEach() { tool = new JDBCTool(); }
     * 
     * @After public void executedAfterEach() { tool = new JDBCTool(); }
     */

    @Test
    public void test_Constructor_Throw_Exception() {
        // Given
        CompanyDAO compa = null;
        BasicJdbc testFail = null;

        try {
            // When
            compa = new CompanyDAO();
            fail("Must throw an exception");
        } catch (IllegalArgumentException e) {
            // Then
            assertEquals(e.getMessage(), "c is null");
        }
    }

    @Test
    public void test_GetAll() {
        // Given
        BasicJdbc tool = new BasicJdbc();
        CompanyDAO compa = new CompanyDAO();
        List<Company> listResult = null;

        // When
        listResult = compa.getAll();

        // Then
        if (listResult == null) {
            fail("The list should not be null");
        } else if (listResult.isEmpty()) {
            fail("The list should not be empty");
        }

    }

    @Test
    public void test_GetId() {
        // Given
        Company comp = new Company();
        comp.setId(1);
        comp.setName("Apple Inc.");
        BasicJdbc tool = new BasicJdbc();
        CompanyDAO compaDAO = new CompanyDAO();
        Company result = null;

        // When
        result = compaDAO.get(1);

        // Then
        assertEquals(result.getId(), comp.getId());
        assertEquals(result.getName(), comp.getName());
    }

    @Test
    public void test_GetId_Throw_Exception_Id_Neg() {
        // Given
        BasicJdbc tool = new BasicJdbc();
        CompanyDAO compaDAO = new CompanyDAO();

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
        BasicJdbc tool = new BasicJdbc();
        CompanyDAO compaDAO = new CompanyDAO();

        try {
            // When
            compaDAO.get(0);
            fail("Should throw an exception");
        } catch (IllegalArgumentException e) {
            // Then
            assertEquals(e.getMessage(), "ID should not be negative or 0");
        }

    }
}
