package com.excilys.dao.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.dao.DAO;
import com.excilys.dao.ExceptionDAO;
import com.excilys.database.PoolJdbc;
import com.excilys.database.SpringDataSource;
import com.excilys.database.VirtualConnectTool;
import com.excilys.mapper.Mapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.utils.EasyConnection;
import com.excilys.utils.OrderType;

public class SpringComputerDAO implements DAO<Computer>{


    private final Logger slf4jLogger = LoggerFactory
            .getLogger(SpringComputerDAO.class);

    private static List<Company> cacheCompanies = null;
    
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);   
    }

    static {
        SpringCompanyDAO comA = (SpringCompanyDAO) SpringDataSource.getContext().getBean("SpringCompanyDAO");
        cacheCompanies = comA.getAll();
    }
    
    @Override
    public List<Computer> getAll() {
        String sql = "SELECT * FROM `computer`;";
        return Mapper.resultToListComputerSpring(this.jdbcTemplate.queryForList(sql), cacheCompanies);
    }

    /**
     * To get a computer linked to the name.
     * 
     * @param name
     *            The name of the computer
     * @return The computer
     */
    public List<Computer> searchFor(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name should not be null");
        }
        String sql = "SELECT * FROM `computer` Inner JOIN company ON computer.company_id = company.id WHERE computer.name = ? OR company.name = ? ;";
        return Mapper.resultToListComputerSpring(this.jdbcTemplate.queryForList(sql, new Object[] {name, name}), cacheCompanies);
    }

    @Override
    public Computer get(long idComp) {
        if (idComp <= 0) {
            throw new IllegalArgumentException("ID should not be negative or 0");
        }
        return (Computer) this.jdbcTemplate.query(new PreparedStatementCreator() {     
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    {
                String sql = "SELECT * FROM `computer` WHERE id = ? ;";
                try {
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setLong(1, idComp);
                    return stmt;
                } catch (SQLException e) {
                    throw new ExceptionDAO(e.getMessage());
                }
                
                
            }
        }, new ResultSetExtractor() {
            @Override
            public Computer extractData(ResultSet rs) {
                try {
                    if (rs.next()) {
                        Computer temp = new Computer();
                        temp.setId(rs.getLong("id"));
                        temp.setName(rs.getString("name"));
                        temp.setIntro(rs.getTimestamp("introduced"));
                        temp.setDisco(rs.getTimestamp("discontinued"));
                        
                        if ((Long) rs.getLong("company_id") != null) {
                            temp.setComp(cacheCompanies.get((int) rs.getLong("company_id")));
                        }
                        return temp;
                    }
                } catch (SQLException e) {
                    throw new ExceptionDAO(e.getMessage());
                }
                return null;
            }
        });
    }

    @Override
    public void create(Computer comp) {
        if (comp == null) {
            throw new IllegalArgumentException("c is null");
        }
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                String sql = "INSERT INTO `computer`(`name`, `introduced`, `discontinued`, `company_id`) VALUES (?,?,?,?);";
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, comp.getName());
                stmt.setTimestamp(2, comp.getIntro());
                stmt.setTimestamp(3, comp.getDisco());
                if (comp.getComp() == null) {
                    stmt.setObject(4, null);
                } else {
                    stmt.setObject(4, comp.getComp().getId());
                }
                return stmt;
            }
        });
    }

    @Override
    public void update(Computer comp) {
        if (comp == null) {
            throw new IllegalArgumentException("c is null");
        }
       
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn)
                     {
                String sql = "UPDATE `computer` SET `name` = ?, `introduced` = ?, `discontinued` = ?, `company_id` = ? WHERE `computer`.`id` = ?;";
                
                PreparedStatement stmt = null;
                try {
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, comp.getName());
                    stmt.setTimestamp(2, comp.getIntro());
                    stmt.setTimestamp(3, comp.getDisco());
                    if (comp.getComp() == null) {
                        stmt.setObject(4, null);
                    } else {
                        stmt.setObject(4, comp.getComp().getId());
                    }
                    stmt.setLong(5, comp.getId());
                } catch (SQLException e) {
                    throw new ExceptionDAO(e.getMessage());
                }
                return stmt;
            }
        });
    }

    @Override
    public void delete(long comp) {
        if (comp < 0) {
            throw new IllegalArgumentException("Comp negative");
        }
        String sql = "DELETE FROM `computer` WHERE `id` = ? ;";
        this.jdbcTemplate.update(sql, new Object[] {comp});
    }

    public void deleteWithCompany(long compaID) {
        if (compaID < 0) {
            throw new IllegalArgumentException("Compa negative");
        }
        String sql = "DELETE FROM `computer` WHERE `company_id` = ? ;";
        
        // TODO : Safe?
        jdbcTemplate.update(sql, new Object[] {compaID});

    }

    /**
     * To have a set of the computers.
     * 
     * @param low
     *            First parameter of the LIMIT
     * @param height
     *            Second parameter of the LIMIT
     * @return The List who represents the set of computers
     */
    public List<Computer> getSet(int low, int height) {
        if (low < 0 || height < 0) {
            throw new IllegalArgumentException("Negative param");
        }
        
        String sql = "SELECT * FROM `computer` LIMIT ?,? ;";
        return Mapper.resultToListComputerSpring(this.jdbcTemplate.queryForList(sql, new Object[] {low, height}), cacheCompanies);
    }

    /**
     * @see getSet with a order.
     * @param low
     *            First parameter of the LIMIT
     * @param height
     *            Second parameter of the LIMIT
     * @param ord
     *            The order of the list
     * @return The List who represents the set of computers
     */
    public List<Computer> getSet(int low, int height, OrderType ord) {
        if (low < 0 || height < 0) {
            throw new IllegalArgumentException("Negative param");
        }
        String order[] = ord.toString().split(";");

        String sql = "SELECT * FROM `computer` ORDER BY " + order[0] + " "
                + order[1] + " LIMIT ?,? ;";
        return Mapper.resultToListComputerSpring(this.jdbcTemplate.queryForList(sql, new Object[] {low, height}), cacheCompanies);
    }

    /**
     * To get the number of computers.
     * 
     * @return Nemeric who represent the number of computers
     */
    public long getSizeTable() {
        String sql = "SELECT COUNT(*) FROM `computer`;";
        return this.jdbcTemplate.queryForObject(sql, Long.class);
    }
}
