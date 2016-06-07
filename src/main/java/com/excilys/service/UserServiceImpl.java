package com.excilys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.User;
import com.excilys.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRep;

    @Transactional
    public User getUser(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name is null !");
        }
        return userRep.findUserByName(name);
    }
    
    @Transactional
    public List<User> getAllUsers() {
        return userRep.findAll();
    }
    
    @Transactional
    public void createUser(User u) {
        if (u == null) {
            throw new IllegalArgumentException("User is null !");
        }
        userRep.save(u);
    }
    
    

}
