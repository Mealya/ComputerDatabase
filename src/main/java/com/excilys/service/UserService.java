package com.excilys.service;

import java.util.List;


import com.excilys.model.User;

public interface UserService {
    
    /**
     * A user associate to a name.
     * @param name The name of the user
     * @return The user 
     */
    public User getUser(String name);
    
    /**
     * List all the users in DB.
     * @return The list of the users
     */
    public List<User> getAllUsers();
    
    
    /**
     * Create a user in the DataBase.
     * @param u The user
     */
    public void createUser(User u);
    
}
