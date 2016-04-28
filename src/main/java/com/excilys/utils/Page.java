package com.excilys.utils;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

    private int totalElements;
    private int pageNumber;
    private int elementPerPage;
    private List<T> entities;

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getElementPerPage() {
        return elementPerPage;
    }

    public void setElementPerPage(int elementPerPage) {
        this.elementPerPage = elementPerPage;
    }

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    /**
     * Add an entity to the current list of entities.
     * @param entity to add to the list
     */
    public void addEntity(T entity) {
        if (entities == null) {
            entities = new ArrayList<>();
        }

        entities.add(entity);
    }

}
