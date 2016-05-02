package database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.excilys.database.BasicJdbc;

public class TestJdbcTool {

    @Test
    public void test_Connect_Throw_Exception() {
        //Given
        BasicJdbc tool = new BasicJdbc();
        String name = null;
        
        try {
          //When
            tool.connect(name);
            fail("Must throw an exception");
        } catch (IllegalArgumentException e) {
            //Then
            assertEquals(e.getMessage(), "Name must not be null");
        }
    }
    
    @Test
    public void test_getConnection_Throw_Exception() {
        //Given
        BasicJdbc tool = new BasicJdbc();
        String name = null;
        
        try {
          //When
            tool.getConnection();
            fail("Must throw an exception");
        } catch (IllegalArgumentException e) {
            //Then
            assertEquals(e.getMessage(), "Name must not be null");
        }
    }
    
    @Test
    public void test_closeConnect_Throw_Exception() {
        //Given
        BasicJdbc tool = new BasicJdbc();
        String name = null;
        
        try {
          //When
            tool.closeConnect();
            fail("Must throw an exception");
        } catch (IllegalArgumentException e) {
            //Then
            assertEquals(e.getMessage(), "Name must not be null");
        }
    }

}
