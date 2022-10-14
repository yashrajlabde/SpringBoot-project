package com.springboot.dao;

import com.springboot.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer>{

    @Query("select u FROM User u")           //hibernate
    public List<User> getAllUser();

    public  List<User>findByName(String name);



}
