package com.springboot.services;

import com.springboot.dao.UserRepository;
import com.springboot.entities.User;
import com.springboot.helper.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    UserRepository userRepository;

    public void save(MultipartFile file){
        try{
            List<User> users= CSVHelper.csvToUser(file.getInputStream());
            userRepository.saveAll(users);

        }catch(IOException e){
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}

