package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public class Mapper {

    /**
     * Map a result set to a List of computers.
     * 
     * @param res
     *            The ResultSet
     * @param companies
     *            The company list
     * @return The list of computers
     * @throws SQLException
     *             Error with SQL
     */
    @Deprecated
    public static List<Computer> resultSetToListComputer(ResultSet res,
            List<Company> companies) throws SQLException {
        List<Computer> result = new ArrayList<Computer>();
        // Extract data from result set
        while (res.next()) {
            Computer compuTemp = new Computer();
            compuTemp.setId(res.getLong("id"));
            compuTemp.setName(res.getString("name"));
            compuTemp.setIntro(res.getTimestamp("introduced"));
            compuTemp.setDisco(res.getTimestamp("discontinued"));

            /*
             * for (Company c : companies) { if (c.getId() ==
             * res.getLong("company_id")) { compuTemp.setComp(c); } }
             */
            compuTemp.setComp(companies.get((int) res.getLong("company_id")));
            result.add(compuTemp);

        }
        return result;
    }

    /**
     * Map a result set to a computer.
     * 
     * @param res
     *            The ResultSet
     * @param companies
     *            The company list
     * @return The computer
     * @throws SQLException
     *             Error with SQL
     */
    @Deprecated
    public static Computer resultSetToComputer(ResultSet res,
            List<Company> companies) throws SQLException {
        Computer result = new Computer();
        // Extract data from result set
        while (res.next()) {
            Computer compuTemp = new Computer();
            compuTemp.setId(res.getLong("id"));
            compuTemp.setName(res.getString("name"));
            compuTemp.setIntro(res.getTimestamp("introduced"));
            compuTemp.setDisco(res.getTimestamp("discontinued"));

            /*
             * for (Company c : companies) { if (c.getId() ==
             * res.getLong("company_id")) { compuTemp.setComp(c); } }
             */
            compuTemp.setComp(companies.get((int) res.getLong("company_id")));
            result = compuTemp;

        }
        return result;
    }

    public static List<Company> resultToListCompanySpring(List<Map<String, Object>> result) {
        List<Company> res = new ArrayList<Company>();
        for (Map<String, Object> m : result) {
            Company temp = new Company();
            temp.setId((long) m.get("id"));
            temp.setName((String) m.get("name"));
            res.add(temp);
        }
        return res;
    }
    public static List<Computer> resultToListComputerSpring(List<Map<String, Object>> result, List<Company> companies) {
        List<Computer> res = new ArrayList<Computer>();
        for (Map<String, Object> m : result) {
            Computer compuTemp = new Computer();
            compuTemp.setId((long) m.get("id"));
            compuTemp.setName((String) m.get("name"));
            compuTemp.setIntro((Timestamp) m.get("introduced"));
            compuTemp.setDisco((Timestamp) m.get("discontinued"));

            if (m.get("company_id") != null) {
                System.out.println(m.get("company_id"));
                compuTemp.setComp(
                                    companies.get(
                                                    ((Long) m.get("company_id")).intValue()-1));
            }
            res.add(compuTemp);
        }
        return res;
    }
    
    public static Computer resultToComputerSpring(List<Map<String, Object>> result, List<Company> companies) {
        Computer compuTemp = null;
        for (Map<String, Object> m : result) {
            compuTemp = new Computer();
            compuTemp.setId((long) m.get("id"));
            compuTemp.setName((String) m.get("name"));
            compuTemp.setIntro((Timestamp) m.get("introduced"));
            compuTemp.setDisco((Timestamp) m.get("discontinued"));

            if (m.get("company_id") != null) {
                compuTemp.setComp(companies.get(((Long) m.get("company_id")).intValue()-1));
            }
        }
        return compuTemp;
    }


}
