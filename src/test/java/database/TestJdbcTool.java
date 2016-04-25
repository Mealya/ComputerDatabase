package database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.excilys.database.JDBCTool;

public class TestJdbcTool {

    @Test
    public void test_Connect_Throw_Exception() {
        //Given
        JDBCTool tool = new JDBCTool();
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
        JDBCTool tool = new JDBCTool();
        String name = null;
        
        try {
          //When
            tool.getConnection(name);
            fail("Must throw an exception");
        } catch (IllegalArgumentException e) {
            //Then
            assertEquals(e.getMessage(), "Name must not be null");
        }
    }
    
    @Test
    public void test_closeConnect_Throw_Exception() {
        //Given
        JDBCTool tool = new JDBCTool();
        String name = null;
        
        try {
          //When
            tool.closeConnect(name);
            fail("Must throw an exception");
        } catch (IllegalArgumentException e) {
            //Then
            assertEquals(e.getMessage(), "Name must not be null");
        }
    }

}
