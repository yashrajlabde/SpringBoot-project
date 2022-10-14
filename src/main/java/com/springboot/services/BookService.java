package com.springboot.services;

import com.springboot.dao.BookRepository;
import com.springboot.entities.Books;
import com.springboot.exception.BookNotFoundException;
import com.springboot.exception.EmptyListException;
import com.springboot.exception.EmptyTitleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

//    private static List<Books> books= new ArrayList<>();
//
//    static {
//        books.add(new Books(1,"james","Atomic habbits"));
//        books.add(new Books(2,"robin","5 Am club"));
//        books.add(new Books(3,"ryan","stoic"));
//    }
//    books.add(new Books(1,"james","Atomic habbits"));
//    books.add(new Books(1,"james","Atomic habbits"));
//    books.add(2,"robin","5 Am club");
//    books.add(3,"ryan","stoic");

    public List<Books> getAllBook(){
        List<Books> list =  (List)bookRepository.findAll();

        if(list.size()<=0) throw new EmptyListException();
        return list;
    }

    @Cacheable(cacheNames = "book", key ="#id")
    public Books getBookById(int id){
        System.out.println("fetching book from the db");
        Books book1 = null;
//        try {
////            book1 = books.stream().filter(e -> e.getId() == id).findFirst().get();
//            book1 = this.bookRepository.findById(id);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
          book1 = this.bookRepository.findById(id);
        if(book1==null) throw new BookNotFoundException();
        return book1;
    }

    public Books addBook(Books b){
//        books.add(b);
        if(b.getTitle().isEmpty() || b.getTitle().length()==0) throw new EmptyTitleException();
        Books res= this.bookRepository.save(b);
        return res;
    }

    @CacheEvict(cacheNames = "book", key = "#id")
    public void deleteBook(int id){
//        books=books.stream().filter(e->e.getId()!=id).collect(Collectors.toList());
        Books ifPresent= getBookById(id);
        if(ifPresent==null) throw new BookNotFoundException();

        bookRepository.deleteById(id);
    }

    @CachePut(cacheNames = "book", key = "#book.id")
    public void updateBook(Books book, int id){
//        books=books.stream().map(b->{
//            if(b.getId()==id){
//                b.setAuthor(book.getAuthor());
//                b.setTitle(book.getTitle());
//            }
//            return b;
//        }).collect(Collectors.toList());
        Books ifPresent= getBookById(id);
        if(ifPresent==null) throw new BookNotFoundException();
        book.setId(id);
        bookRepository.save(book);
    }

    public List<Books> sortByField(String field){
        List<Books> list =  (List)bookRepository.findAll();
        if(list.size()<=0) throw new EmptyListException();
        return (List<Books>) bookRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Books> findProductWithPagination(int offset, int pageSize){
        Page<Books> paginatedBooks= bookRepository.findAll(PageRequest.of(offset, pageSize));
        return paginatedBooks;
    }


}
