package com.excilys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.excilys.model.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long> {

    @Query("FROM Computer compu  WHERE compu.name = (:lastName) OR compu.company.name = (:lastName)")
    public List<Computer> find(@Param("lastName") String lastName);
    
    @Query("FROM Computer compu  WHERE compu.name = (:lastName)")
    public List<Computer> findComputerName(@Param("lastName") String lastName);
    
    @Query("FROM Computer compu  WHERE compu.company.name = (:lastName)")
    public List<Computer> findCompanyName(@Param("lastName") String lastName);
}

