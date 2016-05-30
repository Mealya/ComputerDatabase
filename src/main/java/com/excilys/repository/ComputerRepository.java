package com.excilys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.excilys.model.Computer;
import com.excilys.utils.OrderType;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
    
    //@Query("SELECT c FROM Computer as c Inner JOIN Company as compa WHERE c.name = (:lastName) OR compa.name = (:lastName)")
    //@Query("FROM Computer compu JOIN compu.id_compa compa WHERE compu.name=(:lastName) OR compa.name=(:lastName)")
    //public List<Computer> find(@Param("lastName") String lastName);
    
    /*
    @Query("FROM Computer as c LIMIT (:low),(:height)")
    public List<Computer> set(@Param("low") int low, @Param("height") int height);
    
    @Query("FROM Computer as c ORDER BY (:dataType) (:order) LIMIT (:low),(:height)")
    public List<Computer> set(@Param("low") int low, @Param("height") int height, @Param("dataType") String dataType, @Param("order") String ord);
    */
}

