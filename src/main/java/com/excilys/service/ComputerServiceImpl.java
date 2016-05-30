package com.excilys.service;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import com.excilys.model.Computer;
import com.excilys.repository.CompanyRepository;
import com.excilys.repository.ComputerRepository;
import com.excilys.utils.OrderType;

@Service
public class ComputerServiceImpl implements ComputerService {


    @Resource
    private ComputerRepository computerRep;

    
    @Override
    @Transactional
    public List<Computer> getComputers() {
        return computerRep.findAll();
    }

    @Override
    @Transactional
    public Computer getComputer(Long idCompu) {
        if (idCompu < 0) {
            throw new IllegalArgumentException("Id non valide");
        }
        return computerRep.findOne(idCompu);
    }
    
    @Override
    @Transactional
    public List<Computer> searchFor(String name) {
        return new ArrayList<Computer>();
        /*if (name == null) {
            throw new IllegalArgumentException("Name non valide");
        }
        return computerRep.find(name);*/
    }
    
    @Override
    @Transactional
    public void createComputer(Computer c) {
        if (c == null) {
            throw new IllegalArgumentException("c is null");
        }
        computerRep.save(c);
    }
    
    @Override
    @Transactional(rollbackFor=IllegalArgumentException.class)
    public void updateComputer(Computer c) {
        if (c == null) {
            throw new IllegalArgumentException("c is null");
        }
        Computer updatedCompu = computerRep.findOne(c.getId());
        
       if (updatedCompu == null)
           throw new IllegalArgumentException("c is null");
        
       updatedCompu.setName(c.getName());
       updatedCompu.setIntro(c.getIntro());
       updatedCompu.setDisco(c.getDisco());
       
       updatedCompu.setComp(c.getComp());
    }

    @Override
    @Transactional(rollbackFor=IllegalArgumentException.class)
    public void deleteComputer(Long c) {
        if (c <= 0) {
            throw new IllegalArgumentException("c is negative or 0");
        }
        Computer temp = computerRep.findOne(c);
        
        if (temp == null) {
            throw new IllegalArgumentException("Id non valide");
        }
        computerRep.delete(c);
    }

    @Override
    @Transactional
    public List<Computer> getSetComputer(int low, int height) {
        return new ArrayList<Computer>();
        //return computerRep.set(low, height);
    }
    
    @Override
    @Transactional
    public List<Computer> getSetComputer(int low, int height, OrderType ord) {
        /*if (low >= height) {
            throw new IllegalArgumentException("low >= height");
        }
        if (low < 0) {
            throw new IllegalArgumentException("low is negative");
        }
        if (height < 0) {
            throw new IllegalArgumentException("low is negative");
        }*/
        return new ArrayList<Computer>();
        /*
        String order[] = ord.toString().split(";");
        return computerRep.set(low, height, order[0], order[1]);*/
    }
    
    @Override
    @Transactional
    public long getSizeTable() {
        return computerRep.count();
    }
}
