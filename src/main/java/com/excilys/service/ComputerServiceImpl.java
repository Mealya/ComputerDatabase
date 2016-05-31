package com.excilys.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.mapper.Mapper;
import com.excilys.model.Computer;
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
        if (name == null) {
            throw new IllegalArgumentException("Name non valide");
        }
        Set<Computer> founds = new HashSet<Computer>();
        founds.addAll(computerRep.findComputerName(name));
        founds.addAll(computerRep.findCompanyName(name));
        return new ArrayList<Computer>(founds);
        //return computerRep.find(name);
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
    public List<Computer> getSetComputer(int page, int size) {
       return Mapper.pageToList(computerRep.findAll(new PageRequest(page, size)));
    }
    
    @Override
    @Transactional
    public List<Computer> getSetComputer(int page, int size, OrderType ord) {
        /*if (low >= height) {
            throw new IllegalArgumentException("low >= height");
        }*/
        if (page < 0) {
            throw new IllegalArgumentException("page is negative");
        }
        if (size < 0) {
            throw new IllegalArgumentException("size is negative");
        }
        return Mapper.pageToList(computerRep.findAll(new PageRequest(page, size, new Sort(ord.getOrder(), ord.toString().split(";")[0]))));
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
