package com.springboot.dao;

import com.springboot.entities.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Books,Integer>,CrudRepository<Books,Integer> {
    public Books findById(int id);


}
