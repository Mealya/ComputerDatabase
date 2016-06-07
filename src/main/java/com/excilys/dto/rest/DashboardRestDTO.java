package com.excilys.dto.rest;

import java.util.List;

import com.excilys.model.Computer;

public class DashboardRestDTO {
    
    private List<Computer> computers;
    private long nbComputers;
    private String orderBy;
    private String search;
    private String current_url;
    
    /**
     * @return the computers
     */
    public List<Computer> getComputers() {
        return computers;
    }
    
    /**
     * @param computers the computers to set
     */
    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }
    
    /**
     * @return the nbComputers
     */
    public long getNbComputers() {
        return nbComputers;
    }
    
    /**
     * @param nbComputers the nbComputers to set
     */
    public void setNbComputers(long nbComputers) {
        this.nbComputers = nbComputers;
    }
    
    /**
     * @return the orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }
    
    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    
    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }
    
    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }

    /**
     * @return the current_url
     */
    public String getCurrent_url() {
        return current_url;
    }

    /**
     * @param current_url the current_url to set
     */
    public void setCurrent_url(String current_url) {
        this.current_url = current_url;
    }
    
   
}
