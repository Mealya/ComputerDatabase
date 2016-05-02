package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public class Mapper {

    public static List<Computer> resultSetToListComputer(ResultSet res, List<Company> companies) throws SQLException {
        List<Computer> result = new ArrayList<Computer>();
        // Extract data from result set
        while (res.next()) {
            Computer compuTemp = new Computer();
            compuTemp.setId(res.getLong("id"));
            compuTemp.setName(res.getString("name"));
            compuTemp.setIntro(res.getTimestamp("introduced"));
            compuTemp.setDisco(res.getTimestamp("discontinued"));

            for (Company c : companies) {
                if (c.getId() == res.getLong("company_id")) {
                    compuTemp.setComp(c);
                }
            }
            result.add(compuTemp);

        }
        return result;
    }
    public static Computer resultSetToComputer(ResultSet res, List<Company> companies) throws SQLException {
        Computer result = new Computer();
        // Extract data from result set
        while (res.next()) {
            Computer compuTemp = new Computer();
            compuTemp.setId(res.getLong("id"));
            compuTemp.setName(res.getString("name"));
            compuTemp.setIntro(res.getTimestamp("introduced"));
            compuTemp.setDisco(res.getTimestamp("discontinued"));

            for (Company c : companies) {
                if (c.getId() == res.getLong("company_id")) {
                    compuTemp.setComp(c);
                }
            }
            result = compuTemp;

        }
        return result;
    }
}